package fiuba.hora

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import org.androidannotations.annotations.EActivity

import fiuba.hora.data.SimplePoint
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.RelativeLayout
import fiuba.hora.data.Reloj


/**
 * Activity abstracta que modela la pantalla del ejercicio con reloj.
 * Muestra un reloj y una hora que el usuario debe poder identificar
 * moviendo la aguja de la hora. De hacerlo correctamente se le muestra
 * un mensaje de correcto, de lo contrario se le pide que lo intente
 * nuevamente.
 */
@EActivity(R.layout.activity_main)
abstract class MainActivity : AppCompatActivity() {
    private var centerPoint: SimplePoint? = null
    protected var currentAskedTime: Reloj = Reloj()
    private var currentSelectedTime: Reloj = Reloj()
    protected var hourHandMoved = false
    protected var minHandMoved = false

    /**
     * Crea la vista de la activity a partir del layout.
     * Setea la transición de paso a siguiente activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    inner class CanvasView(context: Context) : View(context) {
        private var mRectF:RectF = RectF()
        private var mPaintBackground:Paint = Paint()
        private var mPaintArea:Paint = Paint()

        init {
            mPaintBackground.color = resources.getColor(R.color.clock_area)
            mPaintArea.color = resources.getColor(R.color.textIcons)
        }
        override fun onDraw(canvas: Canvas) {
            val height = canvas.height * 1f
            val width = canvas.width * 1f
            mRectF.set(0f, 0f, width , height)


            canvas.drawArc(mRectF,
                    0f,
                    360f,
                    true, mPaintBackground)

            drawArc(canvas, mRectF, mPaintArea)
        }
    }

    /**
     * Setea los valores de inicio para las vistas y variables.
     */
    override fun onResume() {
        super.onResume()
        val viewData = getMainViewsData()
        hourHand.setImageResource(R.mipmap.hour)
        minHand.setImageResource(R.mipmap.minutes)
        textAskingTime.setText(viewData.text)
        findTimeTitle.setText(viewData.title)
        initNewGame()
        linearHandOnTouch()
        jugarDeNuevoButton()
        volverButton()
    }

    /**
     * Inicia un nuevo juego
     */
    private fun initNewGame() {
        enableHands()
        while (checkForRepeatedTime()) {
            setAskedTime()
        }
        textTimeTxt!!.text = currentAskedTime.toStringBeutify()
        textTimeDigital!!.text = currentAskedTime.toString()
        pintarZona()
    }

    /**
     * Seteo de la logica del boton de jugar de nuevo.
     * Este boton aparece cuando el usuario acierta a la hora en el reloj.
     * Al clickear el boton el mensaje de resultado desaparece, el boton
     * desaparce, se pide identificar una nueva hora y se habilita de nuevo
     * la aguja de la hora para que el usuario pueda jugar.
     */
    private fun jugarDeNuevoButton() {
        jugar.setOnClickListener {
            resultado.visibility = View.INVISIBLE
            jugar.visibility = View.INVISIBLE
            initNewGame()
        }
    }

    /**
     * Pinta la zona entre las dos agujas del reloj
     */
    private fun pintarZona() {
        val mCanvasView = CanvasView(this)
        val mLayout = findViewById<View>(R.id.clockFrame) as RelativeLayout
        val mImageClock = findViewById<View>(R.id.clock) as ImageView
        val mImageMinHand = findViewById<View>(R.id.minHand) as ImageView
        val mImageHourHand = findViewById<View>(R.id.hourHand) as ImageView
        val mImageCenterClock = findViewById<View>(R.id.centerClock) as ImageView

        mCanvasView.layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
        mLayout.addView(mCanvasView)

        mImageClock.bringToFront()
        mImageMinHand.bringToFront()
        mImageHourHand.bringToFront()
        mImageCenterClock.bringToFront()
    }

    /**
     * Delega en la vista concreta el dibujo del area
     */
    abstract fun drawArc(canvas: Canvas, mRectF: RectF, mPaintArea: Paint)

    /**
     * Obtiene la hora que se le va a pedir al usuario.
     */
    abstract fun setAskedTime()

    /**
     * Obtiene un objeto con datos de las vistas de la clase concreta.
     */
    abstract fun getMainViewsData(): MainViewsData

    /**
     * Habilita o deshabilita las agujas (cada ejercicio puede tener alguna aguja deshabilitada
     */
    abstract fun enableHands()

    /**
     * Funcion que devuelve verdadero si se deberia mostrar mensaje luego de que se movió
     * una aguja. Cada pantalla de juego tiene una logica distinta
     */
    abstract fun shouldShowAnswer(): Boolean

    /**
     * Devuelve verdadero si alguna de las agujas habilitadas está marcando la misma hora que
     * la pedida.
     * Devuelve falso en caso contrario.
     * Sirve para checkear que no se repitan horas de una ronda del juego a la siguiente.
     */
    fun checkForRepeatedTime(): Boolean {
        if ((hourHand.isEnabled) && (currentAskedTime.hours == currentSelectedTime.hours)) {
            return true
        }
        if ((minHand.isEnabled) && (currentAskedTime.minutes == currentSelectedTime.minutes)) {
            return true
        }
        return false
    }

    /**
     * Logica que se ejecuta cuando se toca la vista activeHand (una de las agujas del reloj)
     */
    private fun handOnTouch(activeHand: View, updateTimeFromAngle: (Float) -> Unit) {
        if (!activeHand.isEnabled) return
        activeHand.setOnTouchListener { _, event ->
            initCenterPoint()
            resultado.visibility = View.INVISIBLE
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    val location = IntArray(2)
                    rootLayout!!.getLocationOnScreen(location)
                    val rootLayoutLocationY = location[1]
                    val rotationAngleHour = getRotationAngle(
                            SimplePoint(event.rawX, event.rawY - rootLayoutLocationY),
                            centerPoint)

                    updateTimeFromAngle(rotationAngleHour)
                    rotate(activeHand, rotationAngleHour)
                }
                MotionEvent.ACTION_UP -> {
                    setMovedHand(activeHand)
                    checkAnswer(resultado)
                }
            }
            true
        }
    }

    /**
     * Setea el comportamiento para diferentes tipos de eventos de toque de la pantalla
     * para las agujas de la hora y los minutos.
     * Las agujas se mueven tocandola y arrastrando el dedo.
     */
    private fun linearHandOnTouch() {
        handOnTouch(hourHand, currentSelectedTime::setHourFromAngle)
        handOnTouch(minHand, currentSelectedTime::setMinutesFromAngle)
    }

    private fun setMovedHand(hand: View) {
        if (hand.id == minHand.id) {
            minHandMoved = true
        } else if (hand.id == hourHand.id) {
            hourHandMoved = true
        }
    }

    /**
     * Inicializa la ubicación en la pantalla del punto del centro del reloj.
     */
    private fun initCenterPoint() {
        val x = gameLayout.x + clockFrame.x + clockFrame.width/2
        val y = gameLayout.y + clockFrame.y + clockFrame.height/2
        centerPoint = SimplePoint(x, y)
        //Log.d("log", "the program works perfectly " + adjustToCenter(SimplePoint(), centerPoint!!))
    }

    /**
     * Setea la logia del boton de volver hacia atras (menu principal).
     */
    private fun volverButton() {
        volver1.setOnClickListener {
            val menuIntent = Intent(this, MenuActivity::class.java)
            startActivity(menuIntent)
            finish()
        }
    }

    /**
     * Lógica que se ejecuta cuando el usuario acierta.
     * Se le muestra el mensaje de correcto al usuario, se le muestra el boton
     * de seguir jugando y no puede seguir moviendo la aguja hasta que toque el
     * dicho boton.
     */
    private fun correctAnswer(hourHandView: View, minHandView: View, res: TextView) {
        hourHandView.isEnabled = false
        minHandView.isEnabled = false
        res.setText(R.string.correcto)
        res.visibility = View.VISIBLE
        res.setTextColor(res.resources.getColor(R.color.colorAccent))
        like_text.isSelected = true
        like_text.likeAnimation()
        jugar.visibility = View.VISIBLE
    }

    /**
     * Lógica que se ejecuta cuando el usuario no acierta.
     * Se le muestra el mensaje de intentalo de nuevo al usuario.
     */
    private fun wrongAnswer(res: TextView) {
        res.setText(R.string.intentalo_de_nuevo)
        res.setTextColor(res.resources.getColor(R.color.error_color_material))
        res.visibility = View.VISIBLE
    }

    /**
     * Verifica si la hora indicada por el usuario es correcta.
     * De ser así le muestra un mensaje de correcto, en caso contrario
     * muestra un mensaje de intentalo nuevamente.
     */
    private fun checkAnswer(res: TextView) {
        // make some adjustments for anti clockwise rotation
        //Log.d("log", "Hora Pedida " + currentAskedTime.toString() + " Hora seleccionada " + currentSelectedTime.toString())
        if (currentAskedTime.equals(currentSelectedTime)) {
            correctAnswer(hourHand, minHand, res)
        } else if (!shouldShowAnswer()) {
            return
        } else {
            wrongAnswer(res)
        }
        minHandMoved = false
        hourHandMoved = false
    }

    companion object {
        /***
         * Transforma el punto en un punto matematico con su propia lógica interna.
         * @param point el punto a ser trasladado
         * @param center el centro del circulo
         * @return un punto centrado en el centro del circulo
         */
        fun adjustToCenter(point: SimplePoint, center: SimplePoint): SimplePoint {
            val ans = SimplePoint()
            ans.x = point.x - center.x
            ans.y = (point.y - center.y) * -1
            return ans
        }

        /***
         * Obtiene el angulo de rotación del punto respecto del centro del circulo
         * @param point el punto
         * @param centerPoint el centro del circulo
         * @return el angulo de rotación del punto respecto del centro
         */
        fun getRotationAngle(point: SimplePoint, centerPoint: SimplePoint?): Float {
            val realPoint = adjustToCenter(point, centerPoint!!)
            var angle = Math.toDegrees(Math.atan2(realPoint.y.toDouble(), realPoint.x.toDouble())).toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
        }

        /**
         * Rota la view v en el angulo pasado por parametro respecto de su eje.
         * Este metodo sirve para realizar la rotación de la aguja del reloj
         * respecto del centro del reloj.
         */
        fun rotate(v: View, angle: Float) {
            // make some adjustments for anti clockwise rotation
            v.rotation = (angle - 90f) * - 1
        }
    }
}

/**
 * Wrapper de datos de vistas especificos de las subclases de MainActivity.
 * @title el identificador del recurso string del titulo de la pantalla
 * @text el identificador del recurso string del texto de la pantalla
 */
class MainViewsData(val title: Int, val text: Int)
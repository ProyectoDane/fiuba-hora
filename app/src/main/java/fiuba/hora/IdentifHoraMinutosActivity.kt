package fiuba.hora

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Activity de la pantalla del ejercicio para identificar la hora y los minutos.
 * Muestra un reloj y una hora que el usuario debe poder identificar
 * moviendo la aguja de la hora y la de los minutos. De hacerlo correctamente
 * se le muestra un mensaje de correcto, de lo contrario se le pide que lo intente
 * nuevamente.
 */
class IdentifHoraMinutosActivity : MainActivity() {

    override fun drawArc(canvas: Canvas, mRectF: RectF, mPaintZone: Paint) {
        canvas.drawArc(mRectF,
                0f,
                360f,
                true, mPaintZone)
    }

    override fun enableHands() {
        hourHand.isEnabled = true
        minHand.isEnabled = true
    }

    /**
     * Obtiene un objeto con datos de las vistas de la clase concreta.
     */
    override fun getMainViewsData(): MainViewsData {
        return MainViewsData(
                R.string.ident_hora_mins_title,
                R.string.ident_hora_mins_txt
        )
    }

    /**
     * Setea la hora que se le va a pedir al usuario.
     */
    override fun setAskedTime() {
        val hour = (1..12).shuffled().last()
        val minutes = listOf(0,5,10,15,20,25,30,35,40,45,50,55).shuffled().first()
        currentAskedTime.setTime(hour, minutes)
    }

    /**
     * Funcion que devuelve verdadero si se deberia mostrar mensaje luego de que se movió
     * una aguja. Solo se muestra mensaje si se movieron ambas agujas.
     */
    override fun shouldShowAnswer(): Boolean {
        return (hourHandMoved) && (minHandMoved)
    }
}
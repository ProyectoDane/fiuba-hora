package fiuba.hora

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity de la pantalla del ejercicio para identificar los minutos.
 * Muestra un reloj y los minutos que el usuario debe poder identificar
 * moviendo la aguja de los minutos. De hacerlo correctamente se le muestra
 * un mensaje de correcto, de lo contrario se le pide que lo intente
 * nuevamente.
 */
class IdentifMediasHorasActivity : MainActivity() {

    override fun drawArc(canvas: Canvas, mRectF: RectF, mPaintZone: Paint) {
        canvas.drawArc(mRectF,
                currentAskedTime.hourToAngle,
                (currentAskedTime.minToAngle - currentAskedTime.hourToAngle).rem(360),
                true, mPaintZone)
    }
    override fun enableHands() {
        hourHand.isEnabled = false
        minHand.isEnabled = true
    }
    /**
     * Obtiene un objeto con datos de las vistas de la clase concreta.
     */
    override fun getMainViewsData(): MainViewsData {
        return MainViewsData(
                R.string.ident_mins_title,
                R.string.ident_mins_txt
        )
    }

    /**
     * Obtiene los minutos que se le van a pedir al usuario.
     */
    override fun setAskedTime() {
        val minutes = listOf(0,30).shuffled().first()
        val hour = 0
        currentAskedTime.setTime(hour, minutes)
    }

    /**
     * Funcion que devuelve verdadero si se deberia mostrar mensaje luego de que se movió
     * una aguja. Solo importa si se movio la aguja de los minutos.
     */
    override fun shouldShowAnswer(): Boolean {
        return minHandMoved
    }
}
package fiuba.hora

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Activity de la pantalla del ejercicio para identificar la hora.
 * Muestra un reloj y una hora que el usuario debe poder identificar
 * moviendo la aguja de la hora. De hacerlo correctamente se le muestra
 * un mensaje de correcto, de lo contrario se le pide que lo intente
 * nuevamente.
 */
class IdentifHoraActivity : MainActivity() {

    override fun drawArc(canvas: Canvas, mRectF: RectF, mPaintZone: Paint) {
        val sweepAngle = (currentAskedTime.hourToAngle - currentAskedTime.minToAngle).rem(360)
        canvas.drawArc(mRectF,
                currentAskedTime.minToAngle,
                sweepAngle,
                true, mPaintZone)
    }

    override fun enableHands() {
        hourHand.isEnabled = true
        minHand.isEnabled = false
    }

    /**
     * Obtiene un objeto con datos de las vistas de la clase concreta.
     */
    override fun getMainViewsData(): MainViewsData {
        return MainViewsData(
                R.string.ident_hora_title,
                R.string.ident_hora_txt
        )
    }

    /**
     * Setea la hora que se le va a pedir al usuario.
     */
    override fun setAskedTime() {
        val hour = (1..12).shuffled().last()
        val minutes = 0
        currentAskedTime.setTime(hour, minutes)
    }

    /**
     * Funcion que devuelve verdadero si se deberia mostrar mensaje luego de que se movió
     * una aguja. Solo importa si se movio la aguja de la hora.
     */
    override fun shouldShowAnswer(): Boolean {
        return (hourHandMoved)
    }

}
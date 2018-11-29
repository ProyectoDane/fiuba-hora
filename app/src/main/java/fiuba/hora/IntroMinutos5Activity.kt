package fiuba.hora

import android.content.Intent

/**
 * Activity de la pantalla introductoria a un ejercicio que involucra
 * identificar los minutos en el reloj.
 * Contiene la explicación previa necesaria para resolverlo, tanto sobre
 * el ejercicio como sobre el concepto de los minutos.
 */
class IntroMinutos5Activity : IntroActivity() {

    /**
     * Obtiene el intent con la activity que se desea como siguiente.
     */
    override fun getTheIntent(): Intent {
        return Intent(this, IntroMinutos6Activity::class.java)
    }

    /**
     * Obtiene el intent con la activity del video tutorial.
     */
    override fun getTheTutorial(): Intent {
        return Intent(this, VideoMinutosActivity::class.java)
    }

    /**
     * Obtiene el id del recurso string que tiene el texto para el titulo
     * de la pantalla.
     */
    override fun getTitleId(): Int {
        return R.string.intro_mins_title
    }

    /**
     * Obtiene el id del recurso string que tiene el texto para el cuerpo
     * de la pantalla.
     */
    override fun getTextId(): Int {
        return R.string.intro_mins5_txt
    }

    /**
     * Obtiene el id del recurso de audio que corresponda.
     */
    override fun getAudioId(): Int {
        return R.raw.minutes5
    }

    /**
     * Obtiene el id del recurso de imagen para el cuerpo de la pantalla.
     */
    override fun getImageId(): Int {
        return R.mipmap.clock_minutes9
    }
}

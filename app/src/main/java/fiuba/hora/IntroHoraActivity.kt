package fiuba.hora

import android.content.Intent
import fiuba.hora.data.IntroData

/**
 * Activity de la pantalla introductoria a un ejercicio que involucra
 * identificar las horas en el reloj.
 * Contiene la explicación previa necesaria para resolverlo, tanto sobre
 * el ejercicio como sobre el concepto de las horas.
 */
class IntroHoraActivity : IntroActivity() {

    override val introArray: Array<IntroData> = arrayOf(
            IntroData(R.string.intro_hora_txt, R.mipmap.clock_intro1, R.raw.hours1),
            IntroData(R.string.intro_hora2_txt, R.mipmap.clock_intro1, R.raw.hours2),
            IntroData(R.string.intro_hora3_txt, R.mipmap.clock_intro1, R.raw.hours3),
            IntroData(R.string.intro_hora4_txt, R.mipmap.clock_intro1, R.raw.hours4),
            IntroData(R.string.intro_hora5_txt, R.mipmap.clock_intro2, R.raw.hours5),
            IntroData(R.string.intro_hora6_txt, R.mipmap.clock_intro3, R.raw.hours6)
    )

    /**
     * Obtiene el intent con la activity que se desea como siguiente.
     */
    override fun getTheIntent(): Intent {
        return Intent(this, IdentifHoraActivity::class.java)
    }

    /**
     * Obtiene el intent con la activity del video tutorial.
     */
    override fun getTheTutorial(): Intent {
        return Intent(this, VideoHoraActivity::class.java)
    }

    /**
     * Obtiene el id del recurso string que tiene el texto para el titulo
     * de la pantalla.
     */
    override fun getTitleId(): Int {
        return R.string.intro_hora_title
    }

    /**
     * Obtiene el id del recurso string que tiene el texto para el cuerpo
     * de la pantalla.
     */
    override fun getTextId(): Int {
        return R.string.intro_hora_txt
    }

    /**
     * Obtiene el id del recurso de audio que corresponda.
     */
    override fun getAudioId(): Int {
        return R.raw.hours1
    }

    /**
     * Obtiene el id del recurso de imagen para el cuerpo de la pantalla.
     */
    override fun getImageId(): Int {
        return R.mipmap.clock_intro1
    }
}

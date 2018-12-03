package fiuba.hora

import android.content.Intent
import fiuba.hora.data.IntroData

/**
 * Activity de la pantalla introductoria a un ejercicio que involucra
 * identificar los momentos del día en el reloj.
 * Contiene la explicación previa necesaria para resolverlo, tanto sobre
 * el ejercicio como sobre el concepto de las horas.
 */
class IntroMomentosDia1Activity: IntroActivity() {

    override val introArray: Array<IntroData> = arrayOf(
            IntroData(R.string.intro_momentos1_txt, R.mipmap.clock_wake_up, R.raw.moments1),
            IntroData(R.string.intro_momentos2_txt, R.mipmap.clock_school, R.raw.moments2),
            IntroData(R.string.intro_momentos3_txt, R.mipmap.clock_lunch, R.raw.moments3),
            IntroData(R.string.intro_momentos4_txt, R.mipmap.clock_play, R.raw.moments4)
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
        return R.string.intro_momentos_title
    }
}

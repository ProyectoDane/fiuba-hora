package fiuba.hora

import android.content.Intent
import fiuba.hora.data.IntroData

/**
 * Activity de la pantalla introductoria a un ejercicio que involucra
 * identificar los minutos en el reloj.
 * Contiene la explicación previa necesaria para resolverlo, tanto sobre
 * el ejercicio como sobre el concepto de los minutos.
 */
class IntroMinutosActivity : IntroActivity() {

    override val introArray: Array<IntroData> = arrayOf(
            IntroData(R.string.intro_mins_txt, R.mipmap.clock_minutes, R.raw.minutes1),
            IntroData(R.string.intro_mins2_txt, R.mipmap.clock_minutes, R.raw.minutes2),
            IntroData(R.string.intro_mins3_txt, R.mipmap.clock_minutes1, R.raw.minutes3),
            IntroData(R.string.intro_mins4_txt, R.mipmap.clock_minutes2, R.raw.minutes4),
            IntroData(R.string.intro_mins5_txt, R.mipmap.clock_minutes9, R.raw.minutes5),
            IntroData(R.string.intro_mins6_txt, R.mipmap.clock_minutes, R.raw.minutes8),
            IntroData(R.string.intro_mins7_txt, R.mipmap.clock_minutes11, R.raw.minutes9),
            IntroData(R.string.intro_mins8_txt, R.mipmap.clock_minutes11, R.raw.minutes10),
            IntroData(R.string.intro_mins9_txt, R.mipmap.clock_minutes12, R.raw.minutes11),
            IntroData(R.string.intro_mins10_txt, R.mipmap.clock_minutes13, R.raw.minutes12),
            IntroData(R.string.intro_mins11_txt, R.mipmap.clock_minutes14, R.raw.minutes13)
    )

    /**
     * Obtiene el intent con la activity que se desea como siguiente.
     */
    override fun getTheIntent(): Intent {
        return Intent(this, IdentifMinutosActivity::class.java)
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
}

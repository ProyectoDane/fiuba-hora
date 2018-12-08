package fiuba.hora

import android.content.Intent
import fiuba.hora.data.IntroData

/**
 * Activity de la pantalla introductoria a un ejercicio que involucra
 * identificar la hora mas los minutos en el reloj.
 * Contiene la explicación previa necesaria para resolverlo
 */
class IntroHoraMinutosActivity : IntroActivity() {

    override val introArray: Array<IntroData> = arrayOf(
            IntroData(R.string.intro_hora2_txt, R.mipmap.clock_intro1, R.raw.hours2),
            IntroData(R.string.intro_hora4_txt, R.mipmap.clock_intro1, R.raw.hours4),
            IntroData(R.string.intro_hora5_txt, R.mipmap.clock_intro2, R.raw.hours5),
            IntroData(R.string.intro_medias4_txt, R.mipmap.clock_minutes6, R.raw.halfs4),
            IntroData(R.string.intro_cuartos2_txt, R.mipmap.clock_minutes5, R.raw.quarter2),
            IntroData(R.string.intro_cuartos3_txt, R.mipmap.clock_minutes7, R.raw.quarter3),
            IntroData(R.string.intro_mins4_txt, R.mipmap.clock_minutes2, R.raw.minutes4),
            IntroData(R.string.intro_mins5_txt, R.mipmap.clock_minutes9, R.raw.minutes5),
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
        return Intent(this, IdentifHoraMinutosActivity::class.java)
    }

    /**
     * Obtiene el id del recurso string que tiene el texto para el titulo
     * de la pantalla.
     */
    override fun getTitleId(): Int {
        return R.string.intro_hr_min_title
    }
}

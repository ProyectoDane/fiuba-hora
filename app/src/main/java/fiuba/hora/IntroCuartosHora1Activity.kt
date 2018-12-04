package fiuba.hora

import android.content.Intent
import fiuba.hora.data.IntroData

/**
 * Activity de la pantalla introductoria a un ejercicio que involucra
 * identificar las horas en el reloj.
 * Contiene la explicación previa necesaria para resolverlo, tanto sobre
 * el ejercicio como sobre el concepto de las horas.
 */
class IntroCuartosHora1Activity : IntroActivity() {

    override val introArray: Array<IntroData> = arrayOf(
            IntroData(R.string.intro_mins_txt, R.mipmap.clock_minutes, R.raw.minutes1),
            IntroData(R.string.intro_cuartos1_txt, R.mipmap.clock_minutes, R.raw.quarter1),
            IntroData(R.string.intro_cuartos2_txt, R.mipmap.clock_minutes5, R.raw.quarter2),
            IntroData(R.string.intro_cuartos3_txt, R.mipmap.clock_minutes7, R.raw.quarter3)
    )

    /**
     * Obtiene el intent con la activity que se desea como siguiente.
     */
    override fun getTheIntent(): Intent {
        return Intent(this, IdentifCuartosHoraActivity::class.java)
    }

    /**
     * Obtiene el id del recurso string que tiene el texto para el titulo
     * de la pantalla.
     */
    override fun getTitleId(): Int {
        return R.string.intro_cuartos_title
    }
}

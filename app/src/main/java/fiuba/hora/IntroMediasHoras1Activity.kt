package fiuba.hora

import android.content.Intent
import fiuba.hora.data.IntroData

/**
 * Activity de la pantalla introductoria a un ejercicio que involucra
 * identificar las horas en el reloj.
 * Contiene la explicación previa necesaria para resolverlo, tanto sobre
 * el ejercicio como sobre el concepto de las horas.
 */
class IntroMediasHoras1Activity : IntroActivity() {

    override val introArray: Array<IntroData> = arrayOf(
            IntroData(R.string.intro_medias1_txt, R.mipmap.clock_intro1, R.raw.halfs1),
            IntroData(R.string.intro_medias2_txt, R.mipmap.clock_minutes, R.raw.halfs2),
            IntroData(R.string.intro_medias3_txt, R.mipmap.clock_minutes8, R.raw.halfs3),
            IntroData(R.string.intro_medias4_txt, R.mipmap.clock_minutes6, R.raw.halfs4)
    )

    /**
     * Obtiene el intent con la activity que se desea como siguiente.
     */
    override fun getTheIntent(): Intent {
        return Intent(this, IdentifMediasHorasActivity::class.java)
    }

    /**
     * Obtiene el id del recurso string que tiene el texto para el titulo
     * de la pantalla.
     */
    override fun getTitleId(): Int {
        return R.string.intro_medias_title
    }
}

package fiuba.hora

import android.content.Intent


class VideoHoraMinutosActivity : VideoActivity() {


    override fun getTheIntent(): Intent {
        return Intent(this, IdentifHoraMinutosActivity::class.java)
    }

    override fun getTheNameVideo(): String {
        return "explicacion_minutos.mp4"
    }

}

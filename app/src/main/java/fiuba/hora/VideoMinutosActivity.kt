package fiuba.hora

import android.content.Intent


class VideoMinutosActivity : VideoActivity() {


    override fun getTheIntent(): Intent {
        return Intent(this, IdentifMinutosActivity::class.java)
    }

    override fun getTheNameVideo(): String {
        return "explicacion_minutos.mp4"
    }

}

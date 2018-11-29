package fiuba.hora

import android.content.Intent


class VideoHoraActivity : VideoActivity() {


    override fun getTheIntent(): Intent {
        return Intent(this, IdentifHoraActivity::class.java)
    }

    override fun getTheNameVideo(): String {
        return "explicacion_completa.mp4"
    }

}

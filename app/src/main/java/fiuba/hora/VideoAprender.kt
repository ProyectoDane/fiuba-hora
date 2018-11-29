package fiuba.hora

import android.content.Intent


class VideoAprenderActivity : VideoActivity() {


    override fun getTheIntent(): Intent {
        return Intent(this, SeccionesActivity::class.java)
    }

    override fun getTheNameVideo(): String {
        return "explicacion_completa.mp4"
    }

}
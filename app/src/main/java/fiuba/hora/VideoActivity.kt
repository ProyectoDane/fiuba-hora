package fiuba.hora

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView


abstract class VideoActivity : AppCompatActivity() {

    lateinit var mVideoView : VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        mVideoView = findViewById(R.id.SampleVideoView) as VideoView
        val mediaController = MediaController(this)
        mVideoView.setMediaController(mediaController)
        mVideoView.setVideoPath("content://fiuba.hora/" + getTheNameVideo())
        mVideoView.setOnCompletionListener { _ ->
            val intent = getTheIntent()
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            //finish()
        }
        mVideoView.start()
    }

    abstract fun getTheNameVideo(): String
    abstract fun getTheIntent(): Intent
}

package fiuba.hora

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intro.*
import android.media.MediaPlayer

/**
 * Clase abstracta que modela como se debe ver y qué comportamiento
 * deben tener las clases que son de explicaciones introductorias a
 * ejercicios de identificar la hora.
 */
abstract class IntroActivity : AppCompatActivity() {

    var ap: MediaPlayer = MediaPlayer()

    /**
     * Setea la vista con el layout.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    /**
     * Metodo que se llama luego de comenzar la activity.
     * Setea parametros de las vistas para mostrar recursos especificos
     * para cada subclase distinta de esta activity padre.
     * Setea el comportamiento del boton de siguiente para saltar a la
     * activity que corresponda dependiendo de la subclase.
     */
    override fun onResume() {
        super.onResume()
        intro_title.setText(getTitleId())
        intro_txt.setText(getTextId())
        intro_image.setImageResource(getImageId())
        ap = audioPlayer()
        siguiente1.setOnClickListener {
            stopAudioPlayer()
            startActivity(getTheIntent())
            //finish()
        }
        tutorial.setOnClickListener {
            val intent = getTheTutorial()
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            //finish()
        }
    }

    /**
     * Lógica que se ejecuta cuando activity se detiene.
     * Se debe detener el sonido.
     */
    override fun onStop() {
        super.onStop()
        stopAudioPlayer()
    }

    /**
     * Detiene el audio player. Si no se instanció bien desde un principio
     * no tira una excepción al tratar de detenerlo.
     */
    private fun stopAudioPlayer() {
        try {
            ap.stop()
        } catch (e: IllegalStateException) {
        }
    }

    /**
     * Obtengo un media player que esta pasando el audio de la explicación
     * en la app. En caso de excepción, se devuelve un media player vacio.
     */
    private fun audioPlayer(): MediaPlayer {
        try {
            val mp = MediaPlayer.create(this, getAudioId())
            mp.start()
            return mp
        } catch (e: Exception) {
            e.printStackTrace()
            return MediaPlayer()
        }
    }

    /**
     * Obtiene el intent con la activity del video tutorial.
     */
    abstract fun getTheTutorial(): Intent

    /**
     * Obtiene el intent con la activity que se desea como siguiente.
     */
    abstract fun getTheIntent(): Intent

    /**
     * Obtiene el id del recurso string que tiene el texto para el titulo
     * de la pantalla.
     */
    abstract fun getTitleId(): Int

    /**
     * Obtiene el id del recurso string que tiene el texto para el cuerpo
     * de la pantalla.
     */
    abstract fun getTextId(): Int

    /**
     * Obtiene el id del recurso de audio que corresponda.
     */
    abstract fun getAudioId(): Int

    /**
     * Obtiene el id del recurso de imagen para el cuerpo de la pantalla.
     */
    abstract fun getImageId(): Int
}
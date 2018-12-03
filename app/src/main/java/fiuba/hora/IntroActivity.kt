package fiuba.hora

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intro.*
import android.media.MediaPlayer
import android.view.View
import fiuba.hora.data.IntroData

/**
 * Clase abstracta que modela como se debe ver y qué comportamiento
 * deben tener las clases que son de explicaciones introductorias a
 * ejercicios de identificar la hora.
 */
abstract class IntroActivity : AppCompatActivity() {

    var ap: MediaPlayer = MediaPlayer()
    var i = 0
    open val introArray: Array<IntroData> = arrayOf()

    /**
     * Setea la vista con el layout.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    /**
     * Metodo que setea el contenido que se mostrará y reproducirá en la activity luego
     * de que termine de reproducirse el audio actual.
     */
    fun setAudioPlayerListener(audioPlayer: MediaPlayer) {
        audioPlayer.setOnCompletionListener {
            i += 1
            if (i < introArray.size) {
                val currIntroData = introArray[i]
                setLayoutContent(currIntroData.textId, currIntroData.imageId, currIntroData.audioId)
                setAudioPlayerListener(audioPlayer)
            } else{
                finishOnAudioPlayerCompletion(audioPlayer)
            }
        }
    }

    /**
     * Una vez que se reproduo el ultimo audio, se llama a este metodo, que hace que se
     * pase a la siguiente activity (la del ejericico de identificar la hora).
     */
    fun finishOnAudioPlayerCompletion(audioPlayer: MediaPlayer) {
        siguiente1.callOnClick()
    }

    fun setLayoutContent(textId: Int, imageId: Int, audioId: Int) {
        intro_txt.setText(textId)
        intro_txt.invalidate()
        intro_image.setImageResource(imageId)
        intro_image.invalidate()
        ap = audioPlayer(audioId)
        setAudioPlayerListener(ap)
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
        setLayoutContent(getTextId(), getImageId(), getAudioId())
        siguiente1.visibility = View.INVISIBLE
        tutorial.visibility = View.INVISIBLE
        siguiente1.setOnClickListener {
            stopAudioPlayer()
            startActivity(getTheIntent())
        }
        tutorial.setOnClickListener {
            val intent = getTheTutorial()
            startActivity(intent)
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

    override fun onBackPressed() {
        super.onBackPressed()
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
    private fun audioPlayer(audioId: Int): MediaPlayer {
        try {
            val mp = MediaPlayer.create(this, audioId)
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
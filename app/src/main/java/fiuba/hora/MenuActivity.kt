package fiuba.hora

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_menu.*

/**
 * Activity del menu principal de la aplicación
 */
class MenuActivity : AppCompatActivity() {

    /**
     * Crea la vista de la activity a partir del layout.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    /**
     * Setea el comportamiento de los botones de la pantalla.
     * El boton aprender hace que se pase a la activity de elección de tema (hora,
     * minutos, segundos, dia/noche).
     */
    override fun onResume() {
        super.onResume()
        aprender_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SeccionesActivity::class.java)
            startActivity(intent)
        })
        practicar_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SeccionesPracticarActivity::class.java)
            startActivity(intent)
        })
    }
}

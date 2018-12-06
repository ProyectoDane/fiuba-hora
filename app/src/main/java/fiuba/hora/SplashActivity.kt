package fiuba.hora

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

/**
 * Pantalla de splash con logo de DANE.
 * Muestra el logo unos segundos y pasa a la activity de menu principal.
 */
class SplashActivity : AppCompatActivity() {

    /**
     * Crea la vista de la activity a partir del layout.
     * Setea la transición de paso a siguiente activity.
     * Programa el paso a la siguiente activity para dentro
     * de cierta cantidad de segundos.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen()
    }

    /**
     * Programa el paso a la siguiente activity para dentro
     * de cierta cantidad de segundos
     */
    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
                {
                    routeToAppropriatePage()
                    finish()
                },
                splashScreenDuration
        )
    }

    /**
     * Cantidad de milisegundos que deben pasar para saltar
     * a nueva activity.
     */
    private fun getSplashScreenDuration() = 1000L

    /**
     * Logica para saltar a la proxima activity, la activity
     * del menu principal.
     */
    private fun routeToAppropriatePage() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}

package fiuba.hora

import android.content.Intent


/**
 * Activity de la pantalla que muestra un menú para elegir sobre
 * qué tema se desea aprender/ejercitar.
 */
class SeccionesPracticarActivity : SeccionesActivity() {

    /**
     * Ejecuta un salto a la activity al ejercicio de identificar la hora.
     */
    override fun toHoraActivity() {
        toActivity(Intent(this, IdentifHoraActivity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar los minutos.
     */
    override fun toMinActivity() {
        toActivity(Intent(this, IdentifMinutosActivity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar la hora con los minutos
     */
    override fun toHoraMinutosActivity() {
        toActivity(Intent(this, IdentifHoraMinutosActivity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar las medias horas.
     */
    override fun toMediasHorasActivity() {
        toActivity(Intent(this, IdentifMediasHorasActivity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar los cuartos de hora.
     */
    override fun toCuartosHoraActivity() {
        toActivity(Intent(this, IdentifCuartosHoraActivity::class.java))
    }
}
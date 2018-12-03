package fiuba.hora

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import fiuba.hora.data.Seccion

/**
 * Activity de la pantalla que muestra un menú para elegir sobre
 * qué tema se desea aprender/ejercitar.
 */
open class SeccionesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val seccionesArray: Array<Seccion> = arrayOf(
        Seccion("hora", R.drawable.horas, R.color.textIcons, ::toHoraActivity),
        Seccion("medias horas", R.drawable.minutos, R.color.blue1, ::toMediasHorasActivity),
        Seccion("cuartos de hora", R.drawable.minutos, R.color.blue2, ::toCuartosHoraActivity),
        Seccion("minutos", R.drawable.minutos, R.color.blue3, ::toMinActivity),
        Seccion("hora+minutos", R.drawable.segundos, R.color.blue4, ::toHoraMinutosActivity)
        //Seccion("dia/noche", R.drawable.ic_launcher_background, R.color.blue3, ::toMomentosActivity)
    )

    /**
     * Setea la vista a partir del layout.
     * Instancia el recyclerView vacio y su correspondiente adapter, el cual
     * llena el recyclerView a partir del arreglo seccionesArray.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secciones)
        viewManager = LinearLayoutManager(this)
        viewAdapter = SeccionAdapter(seccionesArray, this)
        recyclerView = findViewById<RecyclerView>(R.id.secciones).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    /**
     * Metodo con lógica para saltar a una nueva activity que se encuentra
     * dentro del intent.
     */
    fun toActivity(intent: Intent) {
        startActivity(intent)
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar la hora.
     */
    open fun toHoraActivity() {
        toActivity(Intent(this, IntroHoraActivity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar los minutos.
     */
    open fun toMinActivity() {
        toActivity(Intent(this, IntroMinutosActivity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar la hora con los minutos
     */
    open fun toHoraMinutosActivity() {
        toActivity(Intent(this, IntroHoraMinutosActivity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar los momentos del dia.
     */
    fun toMomentosActivity() {
        toActivity(Intent(this, IntroMomentosDia1Activity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar las medias horas.
     */
    open fun toMediasHorasActivity() {
        toActivity(Intent(this, IntroMediasHoras1Activity::class.java))
    }

    /**
     * Ejecuta un salto a la activity de la introduccion al ejercicio
     * de identificar los cuartos de hora.
     */
    open fun toCuartosHoraActivity() {
        toActivity(Intent(this, IntroCuartosHora1Activity::class.java))
    }
}
package fiuba.hora

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import fiuba.hora.data.Seccion

/**
 * Adapter para la RecyclerView en la pantalla de SeccionesActivity.
 * Contiene la lógica para popular dicha vista con elementos.
 */
class SeccionAdapter(private val seccionesArray: Array<Seccion>,
                     private val activity: SeccionesActivity) :
        RecyclerView.Adapter<SeccionAdapter.SeccionViewHolder>() {

    /**
     * Clase que contiene la vista que estará en cada posición de la recycler view.
     */
    class SeccionViewHolder(cardView: CardView) : RecyclerView.ViewHolder(cardView)

    /**
     * Logica de la creación del view holder.
     * Devuelve el view holder con la vista que contiene (una card view) creada.
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SeccionAdapter.SeccionViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.seccion_list_item, parent, false) as CardView
        return SeccionViewHolder(cardView)
    }

    /**
     * Cuando se bindea la vista que esta en el view holder a la posicion en el
     * recycler view se setean los atributos de dicha vista (CardView).
     */
    override fun onBindViewHolder(holder: SeccionViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        var cardView = (holder.itemView as CardView)
        val seccion = seccionesArray[position]
        cardView.findViewById<TextView>(R.id.seccion).text = seccion.nombre
        cardView.findViewById<ImageView>(R.id.img_seccion).setImageResource(seccion.imgId)
        cardView.setCardBackgroundColor(cardView.resources.getColor(seccion.backColor))
        cardView.setOnClickListener(View.OnClickListener {
            seccion.callback.invoke()
        })
    }

    /**
     * Devuelve la cantidad de views en el recycler view.
     */
    override fun getItemCount() = seccionesArray.size
}
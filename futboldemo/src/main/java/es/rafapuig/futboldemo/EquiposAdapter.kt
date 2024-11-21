package es.rafapuig.futboldemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.rafapuig.futboldemo.databinding.ItemEquipoBinding
import es.rafapuig.futboldemo.model.Equipo

class EquiposAdapter(val equipos: List<Equipo>) : RecyclerView.Adapter<EquipoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.item_equipo, parent, false)

        return EquipoViewHolder(view)

        /*return EquipoViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_equipo, parent, false)
        )*/

    }

    override fun getItemCount() = equipos.size


    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val equipo = equipos[position]
        holder.bind(equipo)
    }
}

class EquipoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemEquipoBinding.bind(view)

    fun bind(equipo: Equipo) {
        with(equipo) {
            binding.let {
                it.equipoNombre.text = nombre
                it.equipoEntrenador.text = entrenador
                it.equipoEstadio.text = estadio
                it.equipoEscudo.setImageResource(escudo)
            }

        }
    }


}
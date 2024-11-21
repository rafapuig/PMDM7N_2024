package es.rafapuig.recyclerviewdemo

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.rafapuig.recyclerviewdemo.databinding.ItemEquipoBinding
import es.rafapuig.recyclerviewdemo.model.Equipo

class EquiposAdapter(val equipos:List<Equipo>) :  RecyclerView.Adapter<EquipoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_equipo, parent, false)

        return EquipoViewHolder(view)
    }

    override fun getItemCount(): Int = equipos.size


    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val equipo = equipos[position]
        holder.bind(equipo)
    }
}

class EquipoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemEquipoBinding.bind(itemView)

    fun bind(equipo:Equipo) {
        binding.equipoNombre.text = equipo.nombre
        binding.equipoEntrenador.text = equipo.entrenador
        binding.equipoEstadio.text = equipo.estadio
        binding.equipoEscudo.setImageResource(equipo.escudo)

        //No va para remoto
        // Hay que usar Glide
        //val uri = Uri.parse("https://github.com/rafapuig/PMDM7N_2024/blob/master/escudos/valencia.png")
        //binding.equipoEscudo.setImageURI(uri)
    }

}
package es.rafapuig.futboldemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import es.rafapuig.futboldemo.databinding.SimpleItemEquipoBinding
import es.rafapuig.futboldemo.model.Equipo

class EquiposFullSpinnerAdapter(context: Context, val equipos: List<Equipo>) :
    EquiposSpinnerAdapter(context, 0, equipos) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.simple_item_equipo, parent, false
        )

        val binding = SimpleItemEquipoBinding.bind(view)

        val equipo = equipos[position]

        binding.equipoNombre.text = equipo.nombre
        binding.equipoEstadio.text = equipo.estadio
        binding.equipoEntrenador.text = equipo.entrenador
        binding.equipoEscudo.setImageResource(equipo.escudo)

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        val binding = if(convertView != null)
            SimpleItemEquipoBinding.bind(convertView)
        else
            SimpleItemEquipoBinding.inflate(LayoutInflater.from(context),parent,false)

        val equipo = equipos[position]

        binding.equipoNombre.text = equipo.nombre
        binding.equipoEstadio.text = equipo.estadio
        binding.equipoEntrenador.text = equipo.entrenador
        binding.equipoEscudo.setImageResource(equipo.escudo)

        return binding.root
    }

}
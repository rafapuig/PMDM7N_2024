package es.rafapuig.futboldemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import es.rafapuig.futboldemo.model.Equipo

open class EquiposSpinnerAdapter(
    private val context: Context,
    @LayoutRes val resource: Int = android.R.layout.simple_spinner_item,
    private val equipos: List<Equipo>
) :
    ArrayAdapter<Equipo>(context, 0, equipos) {

    protected var mDropDownResourceId: Int = android.R.layout.simple_spinner_dropdown_item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_spinner_item, parent, false
        )

        val textView = view as TextView

        val equipo = equipos[position]

        textView.text = equipo.nombre

        return textView
    }

    override fun setDropDownViewResource(resource: Int) {
        mDropDownResourceId = resource
    }


    /*fun getView(position: Int, convertView: View?, parent: ViewGroup, @LayoutRes resource: Int): View {
        return null
    }*/


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(
            mDropDownResourceId, parent, false
        )

        val textView = view as TextView

        val equipo = equipos[position]

        textView.text = equipo.nombre

        return textView
    }
}
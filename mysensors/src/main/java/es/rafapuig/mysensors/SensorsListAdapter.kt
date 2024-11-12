package es.rafapuig.mysensors

import android.content.Context
import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes


class SensorsListAdapter(
    context: Context,
    @LayoutRes val resource: Int,
    private val list: List<Sensor>
) :
    ArrayAdapter<Sensor?>(context, 0, list) {

    private var mDropDownResourceId: Int = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView

        if (view !=null) {
            view = LayoutInflater.from(context).inflate(resource, parent,false)
        }

       /* val view = (convertView ?: LayoutInflater
            .from(context)
            .inflate(resource, parent, false))*/

        val textView = view as TextView

        val sensor = list[position]

        textView.text = sensor.name



        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        val resourceId =
            if (mDropDownResourceId == -1)
                android.R.layout.simple_spinner_dropdown_item
            else
                mDropDownResourceId

        val view = LayoutInflater
            .from(context)
            .inflate(resourceId, parent, false) as TextView

        val sensor = list[position]

        view.text = with(sensor) {
            "$name - (${stringType.split(".").last()})"
        }
        return view
    }

    override fun setDropDownViewResource(resource: Int) {
        mDropDownResourceId = resource
    }
}

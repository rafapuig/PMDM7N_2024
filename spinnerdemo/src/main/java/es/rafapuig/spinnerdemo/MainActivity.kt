package es.rafapuig.spinnerdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.spinnerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val TAG = "Spinner_demo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val planets = resources.getStringArray(R.array.planets)

        planets.forEach { planetName -> Log.i(TAG, "Planeta: $planetName") }

        initViews()
        initListeners()
    }

    private fun initViews() {
        fillPlanetsSpinner()
    }

    private fun fillPlanetsSpinner() {

        val mercurio = Planet("Mercurio", 45450.0)
        val venus = Planet("Venus", 34243254.0)


        val planets =
            listOf(mercurio, venus) //resources.getStringArray(R.array.planets)

        binding.planetsSpinner.adapter = ArrayAdapter(
            this,
            R.layout.mi_spinner_item,
            planets
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }


    private fun initListeners() {

        val listener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?, //el Spinner
                view: View?, // la vista seleccionada
                position: Int, // indice en el aray del elemento seleccionado
                id: Long
            ) {
                val textView = view as TextView
                Log.i(TAG, "Parent: ${parent?.javaClass.toString()}")
                Log.i(TAG, "text: ${textView.text}")
                Log.i(TAG, "Posicion: $position")
                Log.i(TAG, "ID: $id")

                val planet = parent?.getItemAtPosition(position) as Planet
                Log.i(TAG, "${planet.name} ${planet.mass}")

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        binding.planetsSpinner.onItemSelectedListener = listener


    }

}
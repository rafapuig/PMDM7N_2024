package es.rafapuig.futboldemo

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.futboldemo.databinding.ActivitySpinnerBinding
import es.rafapuig.futboldemo.model.EquiposProvider

class SpinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpinnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySpinnerBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initEquiposSpinner()
    }

    private fun initEquiposSpinner() {

        //val nombres = EquiposProvider.EQUIPOS.map { equipo -> equipo.nombre }

        binding.equiposSpinner.adapter = EquiposFullSpinnerAdapter(
            this,
            //android.R.layout.simple_spinner_item,
            EquiposProvider.EQUIPOS
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

    }
}
package es.rafapuig.futboldemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.rafapuig.futboldemo.databinding.ActivityMainBinding
import es.rafapuig.futboldemo.model.EquiposProvider

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fillEquiposRecycler()
    }

    private fun fillEquiposRecycler() {

        val adapter = EquiposAdapter(EquiposProvider.EQUIPOS)
        binding.equiposRecycler.adapter = adapter
        binding.equiposRecycler.layoutManager = LinearLayoutManager(this)

    }


}
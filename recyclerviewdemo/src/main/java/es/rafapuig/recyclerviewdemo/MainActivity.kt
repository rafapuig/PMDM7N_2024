package es.rafapuig.recyclerviewdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import es.rafapuig.recyclerviewdemo.databinding.ActivityMainBinding
import es.rafapuig.recyclerviewdemo.model.EquiposProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        loadRecycler()
    }

    private fun loadRecycler() {
        val adapter = EquiposAdapter(EquiposProvider.EQUIPOS)
        binding.equiposRecyclerview.adapter = adapter
        binding.equiposRecyclerview.layoutManager = LinearLayoutManager(this)
    }
}
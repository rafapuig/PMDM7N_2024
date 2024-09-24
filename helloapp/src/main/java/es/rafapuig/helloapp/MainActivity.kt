package es.rafapuig.helloapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnClickame = findViewById<Button>(R.id.btnClickame)

        btnClickame.text = "Haz click"

        btnClickame.setOnClickListener { onButtonClickCallback(it) }
    }

    private fun onButtonClickCallback(view: View?) {
        val button = view as Button
        Log.i("RAFA", "Has hecho click en el boton: " + button.text)

        val intent = Intent(this, SaludoActivity::class.java)

        val texto = "Hola desde Main"

        intent.putExtra("MENSAJE", texto)

        startActivity(intent)
    }

}
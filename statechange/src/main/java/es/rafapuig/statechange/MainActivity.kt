package es.rafapuig.statechange

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.statechange.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val EDIT_TEXT_KEY = "EditTextKey"
        val CONTADOR_KEY = "ContadorKey"
    }

    val TAG = "StateChanges"

    lateinit var binding: ActivityMainBinding

    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "Llamada a onCreate")

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "LLamada a onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "Llamada a onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Llamada a onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "Llamada a onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "Llamada a onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Llamada a onDestroy")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Log.i(TAG, "Llamada a onRestoreInstanceState")

        val userText = savedInstanceState.getCharSequence(EDIT_TEXT_KEY)

        binding.editText.setText(userText)

        contador = savedInstanceState.getInt(CONTADOR_KEY)
        contador++

        Log.i(TAG, "Veces restaurado = $contador")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "Llamada a onSaveInstanceState")

        val userText = binding.editText.text

        outState.putCharSequence(EDIT_TEXT_KEY, userText)

        outState.putInt(CONTADOR_KEY, contador)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i(TAG, "Llamada a onConfigurationChanged")
    }

}
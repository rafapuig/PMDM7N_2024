package es.rafapuig.addapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnCalcular)
            .setOnClickListener { onButtonCalculateClick() }
    }


    private fun onButtonCalculateClick() {

        try {
            val op1 = findViewById<EditText>(R.id.etOperand1).text.toString().toInt()
            val op2 = findViewById<EditText>(R.id.etOperand2).text.toString().toInt()

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("OP1", op1)
            intent.putExtra("OP2", op2)
            //startActivity(intent)
            startForResultActivity.launch(intent)

        } catch (nfe: NumberFormatException) {
            Toast.makeText(
                this,
                "Introduce valores para los operandos",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val startForResultActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        object : ActivityResultCallback<ActivityResult> {
            override fun onActivityResult(result: ActivityResult) {
                processResultData(result.resultCode, result.data)
            }
        }
    )

    val startForResultActivityl =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result -> processResultData(result.resultCode, result.data) }

    fun processResultData(resultCode: Int, intent: Intent?) {
        Log.i("RAFA", "Procesando los resultados...")

        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Resultado RECHAZADO", Toast.LENGTH_LONG).show()
        }

        if (resultCode == RESULT_OK) {

            if (intent?.hasExtra("RESULTADO") == true) {
                val resultado: Int = intent.getIntExtra("RESULTADO", 0) ?: 0

                Toast
                    .makeText(this, "Resultado $resultado aceptado", Toast.LENGTH_LONG)
                    .show()
            }


        }


    }
}
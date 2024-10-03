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
import es.rafapuig.addapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val OP1 = "es.rafapuig.addapp.MainActivity.OP1"
        val OP2 = "es.rafapuig.addapp.MainActivity.OP2"
    }

    lateinit var binding: ActivityMainBinding

    /*private lateinit var btnButton: Button
    private lateinit var etOperand1: EditText
    private lateinit var etOperand2: EditText*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        //setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //initViews()
        initListeners()
    }

    private fun initListeners() {
        binding.btnCalcular.setOnClickListener { onButtonCalculateClick() }
    }

    /* private fun initViews() {
         btnButton = findViewById(R.id.btnCalcular)
         etOperand1 = findViewById(R.id.etOperand1)
         etOperand2 = findViewById(R.id.etOperand2)
     }*/


    private fun onButtonCalculateClick() {
        try {
            val op1 = binding.etOperand1.text.toString().toInt()
            val op2 = binding.etOperand2.text.toString().toInt()

            val intent = Intent(this, ResultActivity::class.java)

            intent.putExtra(OP1, op1)
            intent.putExtra(OP2, op2)

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


    /* val startForResultActivityAT: ActivityResultLauncher<Intent> = registerForActivityResult(
         ActivityResultContracts.StartActivityForResult(),
         object : ActivityResultCallback<ActivityResult> {
             override fun onActivityResult(result: ActivityResult) {
                 processResultData(result.resultCode, result.data)
             }
         }
     )*/

    private val startForResultActivity =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { processResult(it) }

    private fun processResult(result: ActivityResult) {

        Log.i("RAFA", "Procesando los resultados...")

        when (result.resultCode) {

            RESULT_OK -> {
                val intent = result.data
                if (intent?.hasExtra(ResultActivity.RESULTADO) == true) {
                    val resultado = intent.getIntExtra(ResultActivity.RESULTADO, 0)

                    val message = getString(R.string.approved_result, resultado)
                    Toast
                        .makeText(this, message, Toast.LENGTH_LONG)
                        .show()
                }
            }

            RESULT_CANCELED ->
                Toast.makeText(this, getString(R.string.rejected_result), Toast.LENGTH_LONG).show()

        }

    }

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
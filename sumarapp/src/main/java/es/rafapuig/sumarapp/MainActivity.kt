package es.rafapuig.sumarapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.sumarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val OPERAND_1 = "es.rafapuig.sumarapp.OPERAND_1"
        val OPERAND_2 = "es.rafapuig.sumarapp.OPERAND_2"
        val OPERATION = "es.rafapuig.sumarapp.OPERATION"
    }

    lateinit var binding: ActivityMainBinding

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

        initListeners()
    }

    private fun initListeners() {
        binding.calculateButton.setOnClickListener { onCalculating() }
    }

    private fun onCalculating() {

        val secureToInt = { editText: EditText ->
            with(editText.text.toString()) {
                if (isEmpty()) 0 else toInt()
            }
        }

        /*val operand1 =
            with(binding.editOperand1.text.toString()) {
                if (isEmpty()) 0 else toInt()
            }
        val operand2 =
            with(binding.editOperand2.text.toString()) {
                if (isEmpty()) 0 else toInt()
            }*/

        val operand1 = secureToInt(binding.editOperand1)
        val operand2 = secureToInt(binding.editOperand2)

        requestCalculation(operand1, operand2)
    }

    private fun requestCalculation(operand1: Int, operand2: Int) {

        with(Intent(this, ResultActivity::class.java)) {
            putExtra(OPERAND_1, operand1)
            putExtra(OPERAND_2, operand2)
            putExtra(OPERATION, "+")
            startActivity(this)
        }
    }

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        object : ActivityResultCallback<ActivityResult> {
            override fun onActivityResult(result: ActivityResult) {
                processResultData(result.resultCode, result.data)
            }

        }
    )

    private fun processResultData(resultCode: Int, data: Intent?) {

        /*if (resultCode == RESULT_OK) {
            if (data?.hasExtra(ResultActivity.OPERATION_RESULT) == true) {

            }
        }*/

        val agreed = resultCode == RESULT_OK

        val result = data?.getIntExtra(ResultActivity.OPERATION_RESULT, 0) ?: 0



        val agreement = getString(
            R.string.agree_with_the_solution,
            if (agreed) "Yes, I " else "No, I don't",
            result
        )

        binding.agreementText.text = agreement

    }


}
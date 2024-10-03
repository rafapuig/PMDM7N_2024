package es.rafapuig.addapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    companion object {
        val RESULTADO = "es.rafapuig.addapp.ResultActivity.RESULTADO"
    }

    lateinit var btnOK: Button
    lateinit var btnCancel: Button
    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        initListeners()

        val op1 = intent.getIntExtra(MainActivity.OP1, 0)
        val op2 = intent.getIntExtra(MainActivity.OP2, 0)

        val suma = op1 + op2

        tvResult.text = suma.toString()
    }

    private fun initViews() {
        btnOK = findViewById(R.id.btnOk)
        btnCancel = findViewById(R.id.btnCancel)
        tvResult = findViewById(R.id.tvResult)
    }

    private fun initListeners() {
        btnOK.setOnClickListener { goBack(RESULT_OK) }
        btnCancel.setOnClickListener { goBack(RESULT_CANCELED) }
    }

    private fun goBack(resultCode: Int) {

        if (resultCode == RESULT_OK) {
            val resultado = tvResult.text.toString().toInt()

            val intent = Intent()
            intent.putExtra(RESULTADO, resultado)
            setResult(RESULT_OK, intent)
        }
        finish()
    }

}
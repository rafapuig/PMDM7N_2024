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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val op1 = intent.getIntExtra("OP1", 0)
        val op2 = intent.getIntExtra("OP2", 0)

        val suma = op1 + op2

        findViewById<TextView>(R.id.tvResult).text = suma.toString()


        findViewById<Button>(R.id.btnOk).setOnClickListener {
            val intent = Intent()
            intent.putExtra("RESULTADO", suma)
            setResult(RESULT_OK, intent)
            finish()
        }

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
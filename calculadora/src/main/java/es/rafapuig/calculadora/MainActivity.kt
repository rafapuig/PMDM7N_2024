package es.rafapuig.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class State { InsertOp1, InsertOp2 }

    enum class Operation {Ninguna, Sumar, Restar, Multiplicar, Dividir}

    var state = State.InsertOp1
    var operation = Operation.Ninguna
    var operand1 = 0
    var operand2 = 0


    private lateinit var binding: ActivityMainBinding

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
        with(binding) {
            val keys = listOf(key0, key1, key2, key3, key4, key5, key6, key7, key8, key9)
            keys.forEach { key ->
                key.setOnClickListener { onCipherKey(it) }
            }

            keyAdd.setOnClickListener {
                state = State.InsertOp2
                operation = Operation.Sumar
            }

            keySub.setOnClickListener {
                state = State.InsertOp2
                operation = Operation.Restar
            }

            keyEquals.setOnClickListener {
                operar()
            }
        }
    }

    private fun operar() {
        when(state) {
            State.InsertOp1 -> return
            State.InsertOp2 -> {
                operand2 = operand1 + operand2
                operand1 = operand2
                operand2 = 0
                state = State.InsertOp1
                updateDisplay()
                state = State.InsertOp2
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateDisplay()
    }

    fun onCipherKey(v: View) {
        val buttonKey = v as Button
        val cipher = buttonKey.text.toString().toInt() % 10
        addCipherToOperand(cipher)
        updateDisplay()
    }

    private fun addCipherToOperand(cipher: Int) {
        when (state) {
            State.InsertOp1 -> operand1 = addCipherToOperand(operand1, cipher)
            State.InsertOp2 -> operand2 = addCipherToOperand(operand2, cipher)
        }
    }

    private fun addCipherToOperand(operand: Int, c: Int) = operand * 10 + c



    fun updateDisplay() {
        binding.display.text = when (state) {
            State.InsertOp1 -> operand1
            State.InsertOp2 -> operand2
        }.toString()
    }
}
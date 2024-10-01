package es.rafapuig.weekdaysapp

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var tvWeekDay : TextView
    lateinit var rgWeekDays : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
    }


    private fun initComponents() {
        tvWeekDay = findViewById(R.id.tvSelectedDay)
        rgWeekDays = findViewById(R.id.rgWeekdays)
    }

    private fun initListeners() {
        rgWeekDays.setOnCheckedChangeListener(::changeTextView)
    }

    fun changeTextView(group: RadioGroup, checkedId:Int) {
        tvWeekDay.text = findViewById<RadioButton>(checkedId).text
    }


}
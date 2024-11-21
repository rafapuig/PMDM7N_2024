package es.rafapuig.mysensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.mysensors.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {

    val TAG = "MisSensores"

    private lateinit var binding: ActivityMainBinding

    private lateinit var sensorManager: SensorManager

    private lateinit var sensors: List<Sensor>

    var currentSensor: Sensor? = null


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

        fillSensorsList()

        fillSensorsSpinner()
        initListeners()
    }

    private fun fillSensorsList() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
    }

    fun fillSpinner() {

        val names = sensors.map { sensor -> sensor.name }

        //val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sensors)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            names)

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        binding.sensorsSpinner.adapter = adapter

    }

    fun initSpinnerListener() {
        binding.sensorsSpinner
            .onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //currentSensor = parent?.getItemAtPosition(position) as Sensor
                currentSensor = sensors[position]
                updateUI()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    private fun fillSensorsSpinner() {


        //val list = sensors.map { sensor -> sensor.name }

        binding.sensorsSpinner.adapter = SensorsListAdapter(
            this,
            android.R.layout.simple_spinner_item,
            sensors
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        /*ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            list
        ).also { adapter ->
            adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
        }*/
    }


    private fun initListeners() {

        binding.sensorsSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //currentSensor = sensors[position]

                    //val sensorName = parent?.getItemAtPosition(position) as String

                    /* val sensorName = parent?.run {
                         adapter.getItem(position) as String
                     }

                     currentSensor = sensors.firstOrNull { sensor ->
                         sensor.name == sensorName
                     }*/

                    currentSensor = parent?.getItemAtPosition(position) as Sensor

                    updateUI()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

    }

    private fun updateUI() {

        with(binding) {
            currentSensor?.let {
                sensorType.text = it.stringType
                sensorVendor.text = it.vendor
                sensorVersion.text = "${it.version}"
                sensorResolution.text = getString(R.string.f_units, it.resolution)
                sensorMaxRange.text = getString(R.string.f_units, it.maximumRange)
                sensorPower.text = String.format(Locale.getDefault(), "%f mA", it.power)
            }
        }
    }
}
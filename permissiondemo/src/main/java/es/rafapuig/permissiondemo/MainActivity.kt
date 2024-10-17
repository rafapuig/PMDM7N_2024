package es.rafapuig.permissiondemo

import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager.PERMISSION_GRANTED

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val TAG = "Permission_Demo"

    private val RECORD_REQUEST_CODE = 77

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupPermissions()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.i(TAG, "El permiso ha sido concedido por el usuario")
            } else {
                Log.i(TAG, "El permiso ha sido denegado por el usuario")
            }
        }

    private fun setupPermissions() {
        val permission = checkSelfPermission(RECORD_AUDIO)

        if (permission != PERMISSION_GRANTED) {
            Log.i(TAG, "Permiso para grabar audio no concedido")

            if (shouldShowRequestPermissionRationale(RECORD_AUDIO)) {

                val builder = AlertDialog.Builder(this)

                builder
                    .setMessage(
                        "El permiso para acceder al microfono" +
                                " es necesario para  que la app pueda grabar audio"
                    )

                    .setTitle("Permiso requerido")

                    .setPositiveButton("OK") { dialog, id -> makeRequest() }

                    .create()

                    .show()

            }
            else {
                makeRequest()
            }
        }
    }

    private fun makeRequest() {
        //ActivityCompat
        //    .requestPermissions(this, arrayOf(RECORD_AUDIO), RECORD_REQUEST_CODE)
        //requestPermissions(arrayOf(RECORD_AUDIO), RECORD_REQUEST_CODE)

        requestPermissionLauncher.launch(RECORD_AUDIO)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            RECORD_REQUEST_CODE ->
                if (grantResults.isEmpty() || grantResults[0] != PERMISSION_GRANTED) {
                    Log.i(TAG, "El permiso ha sido denegado por el usuario")
                } else {
                    Log.i(TAG, "El permiso ha sido concedido")
                }
        }

    }
}
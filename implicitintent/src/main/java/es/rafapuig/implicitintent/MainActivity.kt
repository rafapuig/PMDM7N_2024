package es.rafapuig.implicitintent

import android.Manifest.permission.CALL_PHONE
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val callPermissionRequestLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { onRequestCallPermissionResult(it) }


    private fun onRequestCallPermissionResult(isGranted: Boolean) {
        if (isGranted) makePhoneCall()
    }

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
            takePhotoButton.setOnClickListener { onTakingPhoto() }
            callButton.setOnClickListener { onMakingACall() }
            navigateButton.setOnClickListener { onNavigatingToURL() }
            sendEmailButton.setOnClickListener { onSendingEmail() }
        }
    }

    private fun onSendingEmail() {
        val addresses = arrayOf(binding.editEmailAddress.text.toString())
        val subject = binding.editEmailSubject.text.toString()
        val message = binding.editMailBody.text.toString()

        composeEmail(addresses, subject, message)
    }

    private fun composeEmail(addresses: Array<String>, subject: String, message: String) {
        val sendEmail = Intent()

        sendEmail.action = Intent.ACTION_SENDTO

        sendEmail.setDataAndType(Uri.parse("mailto:"), "*/*")

        sendEmail.putExtra(Intent.EXTRA_EMAIL, addresses)
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, subject)
        sendEmail.putExtra(Intent.EXTRA_TEXT, message)

        //if(sendEmail.resolveActivity(packageManager) != null)
            startActivity(sendEmail)
    }

    private fun onNavigatingToURL() {
        val url = getNavigateToURL()
        navigateTo(url)
    }

    private fun navigateTo(url: String) {
        val navigate = Intent()
        navigate.action = Intent.ACTION_VIEW

        val uri = with(url) {
            "${if (!startsWith("https://") && !startsWith("http://"))
                "http://" else ""}$this"
        }

        navigate.data = Uri.parse(uri)
        startActivity(navigate)
    }

    private fun getNavigateToURL() = binding.editUrlToNavigate.text.toString()

    private fun onMakingACall() {
        // Comprueba si la app no tiene permiso concedido para llamar
        if (checkSelfPermission(CALL_PHONE) != PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(CALL_PHONE)) {
                showUIRationaleCallPhone()
            } else {
                requestCallPermission()
            }
        } else {
            makePhoneCall()
        }
    }

    private fun showUIRationaleCallPhone() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.call_phone_permission_request))
            .setMessage(getString(R.string.the_app_needs_you_allow_to_make_phone_calls))
            .setPositiveButton(R.string.ok) { _, _ -> requestCallPermission() }
            .create()
            .show()
    }

    private fun requestCallPermission() {
        callPermissionRequestLauncher.launch(CALL_PHONE)
    }

    private fun makePhoneCall() {
        val phoneNumber = getPhoneNumber()
        callPhone(phoneNumber)
    }

    private fun callPhone(phoneNumber: String) {
        val phoneCall = Intent()
        phoneCall.action = Intent.ACTION_CALL
        phoneCall.data = Uri.parse("tel:$phoneNumber")
        startActivity(phoneCall)
    }

    private fun getPhoneNumber() = binding.editTextPhone.text.toString()


    private fun onTakingPhoto() {
        /*val intent = Intent()
        intent.action = MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA
        startActivity(intent)*/

        with(Intent()) {
            action = MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA
            startActivity(this)
        }


    }
}
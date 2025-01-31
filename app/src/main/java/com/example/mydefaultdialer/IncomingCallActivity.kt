import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telecom.TelecomManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.mydefaultdialer.databinding.ActivityIncomingCallBinding

class IncomingCallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIncomingCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomingCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get phone number from intent
        val phoneNumber = intent.getStringExtra("phone_number") ?: "Unknown"
        binding.tvName.text = phoneNumber

        // Accept call button
        binding.acceptBtn.setOnClickListener {
            acceptCall()
        }

        // Reject call button
        binding.rejectBtn.setOnClickListener {
            rejectCall()
        }
    }

    private fun acceptCall() {
        val telecomManager = getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ANSWER_PHONE_CALLS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                telecomManager.acceptRingingCall()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ANSWER_PHONE_CALLS),
                    REQUEST_CODE_ANSWER_CALL
                )
            }
        }
    }

    private fun rejectCall() {
        val telecomManager = getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ANSWER_PHONE_CALLS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                telecomManager.endCall()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ANSWER_PHONE_CALLS),
                    REQUEST_CODE_ANSWER_CALL
                )
            }
        }
        finish()
    }

    companion object {
        private const val REQUEST_CODE_ANSWER_CALL = 100
    }
}
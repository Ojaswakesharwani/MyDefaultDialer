import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        val phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

        Log.d("CallReceiver", "State: $state, Incoming Number: $phoneNumber")

        if (state == TelephonyManager.EXTRA_STATE_RINGING) {
            val callIntent = Intent(context, IncomingCallActivity::class.java).apply {
                putExtra("phone_number", phoneNumber ?: "Unknown")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(callIntent)
        }
    }
}
package demo.eclipsek20.umpsterminal

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import demo.eclipsek20.umpsterminal.ui.theme.UMPSTerminalDemoTheme

class MainActivity : ComponentActivity() {
    private var mNfcAdapter: NfcAdapter? = null
    private lateinit var basicReader: BasicReader
    val tagStatus = mutableStateOf("Ready to read")
    val tagStatusTime = mutableStateOf(System.currentTimeMillis() / 1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        basicReader = BasicReader(this, tagStatus, tagStatusTime)
        val intent = Intent(this, MyHostApduService::class.java)
        // Toast.makeText(this, "The HCE Service is started", Toast.LENGTH_SHORT).show()
        startService(intent)



        setContent {
            UMPSTerminalDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainUI(
                        getStatus = { tagStatus.value },
                        getStatusTime = { tagStatusTime.value },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    public override fun onResume() {
        super.onResume()
        if (mNfcAdapter != null) {
            if (!mNfcAdapter!!.isEnabled()) showWirelessSettings()
            val options = Bundle()
            // Work around for some broken Nfc firmware implementations that poll the card too fast
            options.putInt(NfcAdapter.EXTRA_READER_PRESENCE_CHECK_DELAY, 250)

            // Enable ReaderMode for NfcA types of card and disable platform sounds
            // the option NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK is NOT set
            // to get the data of the tag after reading
            mNfcAdapter!!.enableReaderMode(
                this,
                basicReader,
                NfcAdapter.FLAG_READER_NFC_A or
                        NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS,
                options
            )
        }
    }

    public override fun onPause() {
        super.onPause()
        mNfcAdapter?.disableReaderMode(this)
    }

    private fun showWirelessSettings() {
        Toast.makeText(this, "You need to enable NFC", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        startActivity(intent)
    }
}

class MyHostApduService : HostApduService() {
    override fun processCommandApdu(commandApdu: ByteArray?, bundle: Bundle?): ByteArray? {
        return ByteArray(0)
    }

    override fun onDeactivated(i: Int) {
    }
}

class BasicReader(
    private val context: Context,
    private val tagStatus:  androidx.compose.runtime.MutableState<String>,
    private val tagStatusTime:  androidx.compose.runtime.MutableState<Long>
) : NfcAdapter.ReaderCallback {

    override fun onTagDiscovered(tag: Tag?) {
        (context as? MainActivity)?.runOnUiThread {
            // Toast.makeText(context, "NFC Tag Discovered!", Toast.LENGTH_SHORT).show()
            playSinglePing(context)
            tagStatus.value = "Tag Detected"
            tagStatusTime.value = System.currentTimeMillis() / 1000
        }
    }

}


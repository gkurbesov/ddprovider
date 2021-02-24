package ru.gkurbesov.ddp.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.gkurbesov.ddp.ddprovider.DDProvider
import ru.gkurbesov.ddp.ddprovider.IDDProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ddp: IDDProvider = DDProvider(this)

        findViewById<TextView>(R.id.lb_android_id).setText("${ddp.getAndroidId()}")
        findViewById<TextView>(R.id.lb_device_name).setText("${ddp.getDeviceName()}")
        findViewById<TextView>(R.id.lb_device_uid).setText("${ddp.getDeviceUid()}")
    }
}
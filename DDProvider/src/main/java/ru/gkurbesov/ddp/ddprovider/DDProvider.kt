package ru.gkurbesov.ddp.ddprovider

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.provider.Settings.Secure
import java.security.MessageDigest

class DDProvider(val context: Context) : IDDProvider {

    private lateinit var androidId: String
    private lateinit var deviceName: String
    private lateinit var deviceUid: String

    override fun getAndroidId(): String {
        if (!this::androidId.isInitialized)
            androidId = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        return androidId
    }

    override fun getDeviceName(): String {
        if (!this::deviceName.isInitialized) {
            deviceName = "${Build.BRAND} ${Build.MODEL.replace(' ', '-')} (${Build.DEVICE})"
        }
        return deviceName
    }

    override fun getDeviceUid(): String {
        if (!this::deviceUid.isInitialized) {
            deviceUid =
                "${getAndroidId()}-${Build.ID}-${hashString(HASH_TYPE_SHA1, getDeviceName())}"
        }
        return deviceUid
    }

    companion object {
        const val HASH_TYPE_SHA1 = "SHA-1"
        const val HEX_CHARS = "0123456789ABCDEF"

        private fun hashString(type: String, input: String): String {
            val bytes = MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
            val result = StringBuilder(bytes.size * 2)
            bytes.forEach {
                val i = it.toInt()
                result.append(HEX_CHARS[i shr 4 and 0x0f])
                result.append(HEX_CHARS[i and 0x0f])
            }
            return result.toString().toLowerCase()
        }
    }
}

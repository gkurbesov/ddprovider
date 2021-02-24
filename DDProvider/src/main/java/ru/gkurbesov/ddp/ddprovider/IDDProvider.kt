package ru.gkurbesov.ddp.ddprovider

import android.content.Context
import android.provider.Settings

interface IDDProvider {
    fun getAndroidId() : String
    fun getDeviceName() : String
    fun getDeviceUid() : String
}
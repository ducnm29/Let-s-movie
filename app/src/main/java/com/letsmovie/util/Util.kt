package com.letsmovie.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.text.intl.Locale

object Util {

    fun getDeviceLanguage() = Locale.current.language

    fun Context.openLinkInBrowser(link: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
package com.letsmovie.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.text.intl.Locale
import com.letsmovie.R

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

    /**
     * Simplify url string
     */
    fun Context.shorterUrl(url: String): String {
        if (url.trim().isEmpty()) {
            return getString(R.string.no_value_data)
        }
        return url.slice(url.indexOf(".") + 1 until url.lastIndexOf("."))
    }
}
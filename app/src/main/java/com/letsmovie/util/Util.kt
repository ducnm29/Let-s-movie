package com.letsmovie.util

import androidx.compose.ui.text.intl.Locale

class Util {
    companion object{
        fun getDeviceLanguage() = Locale.current.language
    }
}
package com.ozyurt.armutapp.util

import android.util.Patterns

object ValidasyonUtil {
    fun mailDogruGirilmisMi(emailText: CharSequence): Boolean {
        if (!emailText.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
            return true
        return false
    }
    fun emailVeSifreAlanıDoluMu(metin: String): Boolean {
        if (!metin.isNullOrBlank()){
            return true
        }else{
            return false
        }
    }
}
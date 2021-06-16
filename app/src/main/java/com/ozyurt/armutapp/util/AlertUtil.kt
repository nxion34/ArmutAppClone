package com.ozyurt.armutapp.util

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.ozyurt.armutapp.R

object AlertUtil {
    fun alertDialogGoster(
        activity: Activity,
        baslik: String,
        mesaj: String,
        internetAlertiMi: Boolean
    ) {
        val builder = AlertDialog.Builder(activity)
        builder.apply {
            setTitle(baslik)
            setMessage(mesaj)

            if (internetAlertiMi) {
                setNegativeButton(R.string.cikisUyari3,
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        activity.finish()
                    })
                setPositiveButton(R.string.ayarlaraGit,
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        activity.startActivity(Intent(Settings.ACTION_SETTINGS))
                        activity.finish()
                    })
            } else {
                setNegativeButton(R.string.hayirUyari,
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })
                setPositiveButton(R.string.evetUyari,
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                        activity.finish()
                    })
            }
            show()
        }
    }
}
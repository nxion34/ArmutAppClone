package com.ozyurt.armutapp.ui.kategoriler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozyurt.armutapp.R
import com.ozyurt.armutapp.util.AlertUtil

class KategorilerActivity : AppCompatActivity() {

    override fun onBackPressed() {
        AlertUtil.alertDialogGoster(
            this@KategorilerActivity,
            getString(R.string.cikisUyari),
            getString(R.string.cikisUyari2),
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategoriler)
    }
}
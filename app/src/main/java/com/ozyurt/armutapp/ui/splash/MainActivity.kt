package com.ozyurt.armutapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.ozyurt.armutapp.R
import com.ozyurt.armutapp.ui.giris.GirisActivity
import com.ozyurt.armutapp.util.AlertUtil
import com.ozyurt.armutapp.util.Constant
import com.ozyurt.armutapp.util.InternetUtil

class MainActivity : AppCompatActivity() {
    var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        splash()

    }

    private fun splash() {
        countDownTimer = object : CountDownTimer(Constant.TİMER_SURE, Constant.TİMER_ARALIK) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                if (InternetUtil.internetKontrol(applicationContext))
                    ekraniDegistir()
                else
                    AlertUtil.alertDialogGoster(this@MainActivity, getString(R.string.internetUyariBaslik), getString(R.string.internetUyariAltBaslik),true)
            }
        }.start()
    }

    private fun ekraniDegistir() {
        var girisIntent = Intent(this@MainActivity, GirisActivity::class.java)
        startActivity(girisIntent)
        finish()
    }
}
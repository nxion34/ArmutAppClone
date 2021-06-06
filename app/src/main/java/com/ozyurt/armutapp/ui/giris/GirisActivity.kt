package com.ozyurt.armutapp.ui.giris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozyurt.armutapp.R
import com.ozyurt.armutapp.databinding.ActivityGirisBinding
import com.ozyurt.armutapp.ui.kategoriler.KategorilerActivity
import com.ozyurt.armutapp.util.AlertUtil
import com.ozyurt.armutapp.util.ValidasyonUtil

class GirisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGirisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnGirisYap.setOnClickListener {
                if (ValidasyonUtil.mailDogruGirilmisMi(editTextTextEmailAddress.text) && !editTextTextPassword.text.isNullOrEmpty()){
                    if (editTextTextPassword.text.toString() == "1613Mert."){
                        val kategoriIntent = Intent(this@GirisActivity, KategorilerActivity::class.java)
                        startActivity(kategoriIntent)
                        finish()
                    }else{
                        txtEmailHataMesaj.text = ""
                        txtSifreHataMesaj.text = "Şifre hatalı girildi"
                    }
                }else{
                    txtSifreHataMesaj.text = "Lütfen şifeni gir"
                    txtEmailHataMesaj.text = "Lütfen email gir"
                }
            }
        }
    }
}
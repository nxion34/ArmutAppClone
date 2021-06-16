package com.ozyurt.armutapp.ui.giris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ozyurt.armutapp.R
import com.ozyurt.armutapp.data.model.KullaniciRespons
import com.ozyurt.armutapp.data.model.Kullanicilar
import com.ozyurt.armutapp.databinding.ActivityGirisBinding
import com.ozyurt.armutapp.ui.kategoriler.KategorilerActivity
import com.ozyurt.armutapp.util.AlertUtil
import com.ozyurt.armutapp.util.ValidasyonUtil

class GirisActivity : AppCompatActivity() {


    lateinit var binding: ActivityGirisBinding
    var viewModelKullanici = ViewModelKullanici()
    var kullanici: KullaniciRespons? = null
    var girenKullanici: Kullanicilar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityGirisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViewModel()

        binding.apply {
            btnGirisYap.setOnClickListener {
                girisYap()
            }
        }
    }

    private fun girisYap() {
        binding.apply {
            btnGirisYap.setOnClickListener {
                if (ValidasyonUtil.mailDogruGirilmisMi(editTextTextEmailAddress.text) && !editTextTextPassword.text.isNullOrEmpty()){
                    kullaniciyiBul(editTextTextEmailAddress.text.toString())
                    if (editTextTextPassword.text.toString() == girenKullanici!!.Sifre && girenKullanici != null){
                        val kategoriIntent = Intent(this@GirisActivity, KategorilerActivity::class.java)
                        startActivity(kategoriIntent)
                        finish()
                    }else{
                        txtEmailHataMesaj.text = ""
                        txtSifreHataMesaj.text = "Şifre hatalı girildi"
                    }
                }else{
                    txtSifreHataMesaj.text = "Lütfen şifreni gir"
                    txtEmailHataMesaj.text = "Lütfen email gir"
                }
            }
        }    }

    private fun initializeViewModel() {
        viewModelKullanici.apply {
            kullaniciLiveData.observe(this@GirisActivity, androidx.lifecycle.Observer {
                Log.e("Mertcan", "observe: " + it.toString())
                kullanici = it
            })

            loading?.observe(this@GirisActivity, androidx.lifecycle.Observer {
                Log.e("Mertcan", "loading:")

            })
        }
    }

    fun kullaniciyiBul(arananEmail: String) {
        kullanici?.Kullanicilar.let {
            var bulunanListe = it!!.filter { it.Email.contains(arananEmail) }
            if (bulunanListe.isNotEmpty()) {
                girenKullanici = bulunanListe.first()
            } else {
                girenKullanici = null
            }
        }
    }
}


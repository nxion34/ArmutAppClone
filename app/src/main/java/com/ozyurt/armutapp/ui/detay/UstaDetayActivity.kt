package com.ozyurt.armutapp.ui.detay

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozyurt.armutapp.R
import com.ozyurt.armutapp.data.model.Ustalar
import com.ozyurt.armutapp.databinding.ActivityUstaDetayBinding
import com.ozyurt.armutapp.util.Constant
import com.ozyurt.armutapp.util.GlideUtil
import com.ozyurt.armutapp.util.NesneUtil

class UstaDetayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUstaDetayBinding
    var ustalar: Ustalar? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usta_detay)
        init()
    }
    private fun init(){
        binding = ActivityUstaDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ustaDetay = intent. getStringExtra(Constant.TASİNAN_KATEOGRİ)
        ustalar = NesneUtil.jsonStringdenNesneye(ustaDetay!!)

        binding.apply {
            ustalar?.let {
                txtAciklama.text = it.Aciklamasi
                txtUstaKat.text = it.Kategorisi
                txtUstaMail.text = it.MailAdresi
                txtUstaAdi.text = it.UstaAdi
                txtUstaTelNo.text = it.Telefonu


                GlideUtil.resmiIndirGoster(applicationContext, it.Fotografi, imgUstaKapak)
                GlideUtil.resmiIndirGoster(applicationContext, it.Fotografi, ustaResim)
            }
        }
    }
}
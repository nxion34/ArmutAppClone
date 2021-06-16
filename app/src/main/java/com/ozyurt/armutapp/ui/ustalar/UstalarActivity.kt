package com.ozyurt.armutapp.ui.ustalar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ozyurt.armutapp.R
import com.ozyurt.armutapp.data.model.KategorilerUstalarResponsItem
import com.ozyurt.armutapp.data.model.Ustalar
import com.ozyurt.armutapp.databinding.ActivityUstalarBinding
import com.ozyurt.armutapp.ui.detay.UstaDetayActivity
import com.ozyurt.armutapp.ui.kategoriler.KategoriAdaptor
import com.ozyurt.armutapp.util.Constant
import com.ozyurt.armutapp.util.NesneUtil
import com.ozyurt.armutapp.util.OnItemClickListener

class UstalarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUstalarBinding
    private var secilenKategori: KategorilerUstalarResponsItem?=null
    var ustalarAdaptor : UstalarAdaptor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityUstalarBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var ustalarJsonString = intent.getStringExtra(Constant.TASİNAN_KATEOGRİ)
        secilenKategori = NesneUtil.jsonStringdenNesneye(ustalarJsonString!!)


        initRecycleView(secilenKategori!!.Ustalar)

        binding.apply {

        }

    }

    private fun initRecycleView(ustalar: List<Ustalar>) {
        binding.apply {
            ustalarAdaptor = UstalarAdaptor(ustalar, object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(
                        applicationContext,
                        ustalar.get(position).UstaAdi,
                        Toast.LENGTH_SHORT
                    ).show()

                    val ustaDetayString = NesneUtil.nesnedenJsonStringe(ustalar.get(position))
                    val intent = Intent(this@UstalarActivity,UstaDetayActivity::class.java)
                    intent.putExtra(Constant.TASİNAN_KATEOGRİ,ustaDetayString)
                    startActivity(intent)
                }
            })

            rcwKategoriler.adapter = ustalarAdaptor
            rcwKategoriler.layoutManager =
                GridLayoutManager(applicationContext, Constant.GRID_KOLON_SAYİ)
        }
    }



}
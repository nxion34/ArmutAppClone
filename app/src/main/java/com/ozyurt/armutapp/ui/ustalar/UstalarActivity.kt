package com.ozyurt.armutapp.ui.ustalar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
    private var ustaList:List<Ustalar>?=null
    private var secilenKategori: KategorilerUstalarResponsItem?=null
    //var ustalarAdaptor : UstalarAdaptor?=null
    private lateinit var ustalarAdaptor: UstalarAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityUstalarBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var ustalarJsonString = intent.getStringExtra(Constant.TASİNAN_KATEOGRI)
        secilenKategori = NesneUtil.jsonStringdenNesneye(ustalarJsonString!!)


        initRecycleView(secilenKategori!!.Ustalar)

        binding.apply {
            btnSirala.setOnClickListener{
                alertListele()
            }
            switchRcwDegis.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    rcwKategoriler.layoutManager = StaggeredGridLayoutManager(Constant.GRID_KOLON_SAYİ, StaggeredGridLayoutManager.VERTICAL)
                }
                else {
                    rcwKategoriler.setLayoutManager( LinearLayoutManager(applicationContext))
                }
            })

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
                    intent.putExtra(Constant.TASİNAN_KATEOGRI,ustaDetayString)
                    startActivity(intent)
                }
            })

            rcwKategoriler.adapter = ustalarAdaptor
            rcwKategoriler.layoutManager =
                GridLayoutManager(applicationContext, Constant.GRID_KOLON_SAYİ)
        }
    }

    private fun alertListele() {
        val builder = AlertDialog.Builder(this@UstalarActivity)
        builder.setTitle(resources.getString(R.string.filtre_sirala))
        val siralama = arrayOf(resources.getString(R.string.filtre_sirala_artan),resources.getString(R.string.filtre_sirala_azalan))
        builder.setItems(siralama) { dialog, pozisyon ->
            when (pozisyon) {
                0 -> {
                    ustaList?.let{
                        var urunListFiltre = it.sortedBy { it.UstaAdi }
                        ustalarAdaptor.setData(urunListFiltre)
                        ustalarAdaptor.notifyDataSetChanged()
                    }
                }
                1 -> {
                    ustaList?.let{
                        var urunListFiltre = it.sortedByDescending { it.UstaAdi }
                        ustalarAdaptor.setData(urunListFiltre)
                        ustalarAdaptor.notifyDataSetChanged()
                    }
                }

            }
            binding.btnSirala.text=siralama.get(pozisyon)
        }
        val dialog = builder.create()
        dialog.show()    }








}
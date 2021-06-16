package com.ozyurt.armutapp.ui.kategoriler

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ozyurt.armutapp.R
import com.ozyurt.armutapp.data.model.KategorilerUstalarRespons
import com.ozyurt.armutapp.data.model.KategorilerUstalarResponsItem
import com.ozyurt.armutapp.data.model.Ustalar
import com.ozyurt.armutapp.databinding.ActivityKategorilerBinding
import com.ozyurt.armutapp.ui.ustalar.UstalarActivity
import com.ozyurt.armutapp.util.AlertUtil
import com.ozyurt.armutapp.util.Constant
import com.ozyurt.armutapp.util.NesneUtil
import com.ozyurt.armutapp.util.OnItemClickListener

class KategorilerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKategorilerBinding
    var kategorilerViewModel: KategorilerViewModel = KategorilerViewModel()

    var kategorilerListe = KategorilerUstalarRespons()
    var kategoriAdaptor: KategoriAdaptor? = null


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
        init()
    }

    fun init() {
        binding = ActivityKategorilerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            srchViewKategoriler.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        kategoriFiltre(newText)
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }
                })
            }
        }

        initViewModel()
    }

    fun kategoriFiltre(newText: String?) {
        newText?.let {
            kategorilerListe?.let {
                var kategoriFiltrelenmisHali =
                    it.filter { it.KategoriAdi!!.toUpperCase().contains(newText.toUpperCase()) }
                initRecycleView(kategoriFiltrelenmisHali)
            }
        }
    }


    fun initViewModel() {

        kategorilerViewModel.apply {
            kategorilerLiveData.observe(this@KategorilerActivity, Observer {
                Log.e("mert", "observe:" + it.toString())
                kategorilerListe = it
                initRecycleView(kategorilerListe!!)

            })

            error?.observe(this@KategorilerActivity, Observer {

                Log.e("mert", "error:")
            })

            loading?.observe(this@KategorilerActivity, Observer {
                Log.e("mert", "loading:")

            })
        }
    }

    private fun initRecycleView(kategoriler: List<KategorilerUstalarResponsItem>) {
        binding.apply {
            kategoriAdaptor = KategoriAdaptor(kategoriler, object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(
                        applicationContext,
                        kategoriler.get(position).KategoriAdi,
                        Toast.LENGTH_SHORT
                    ).show()

                    val ustaStringi = NesneUtil.nesnedenJsonStringe(kategoriler.get(position))

                    val ustaIntent = Intent(this@KategorilerActivity, UstalarActivity::class.java)
                    ustaIntent.putExtra(Constant.TASİNAN_KATEOGRİ, ustaStringi)
                    startActivity(ustaIntent)
                }
            })

            rcwKategoriler.adapter = kategoriAdaptor
            rcwKategoriler.layoutManager =
                GridLayoutManager(applicationContext, Constant.GRID_KOLON_SAYİ)
        }
    }

}
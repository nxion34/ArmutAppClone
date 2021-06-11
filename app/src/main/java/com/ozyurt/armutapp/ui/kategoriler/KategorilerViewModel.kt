package com.ozyurt.armutapp.ui.kategoriler

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ozyurt.armutapp.data.model.KategorilerUstalarRespons
//import com.ozyurt.armutapp.data.model.Kategoriler
import com.ozyurt.armutapp.data.repository.KategoriRepository
import com.ozyurt.armutapp.util.ResourceStatus
import kotlinx.coroutines.launch


class KategorilerViewModel : ViewModel() {

    private val kategoriRepository: KategoriRepository = KategoriRepository()

    init {
        tumKategorileriGetir()
    }

    var loading: MutableLiveData<Boolean>? = MutableLiveData()
    var kategorilerLiveData = MutableLiveData<KategorilerUstalarRespons>()
    var error = MutableLiveData<Throwable>()


    fun tumKategorileriGetir() = viewModelScope.launch {

        kategoriRepository.kategorileriGetir()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("mert", it.data.toString())
                        kategorilerLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}

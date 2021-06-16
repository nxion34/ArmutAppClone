package com.ozyurt.armutapp.ui.giris

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ozyurt.armutapp.data.model.KullaniciRespons
import com.ozyurt.armutapp.data.repository.KullaniciRepository
import com.ozyurt.armutapp.util.ResourceStatus
import kotlinx.coroutines.launch

class ViewModelKullanici: ViewModel() {
    private val kullaniciRepository: KullaniciRepository = KullaniciRepository()

    init {
        kullanicilariGetir()
    }

    var loading: MutableLiveData<Boolean>? = MutableLiveData()
    var kullaniciLiveData = MutableLiveData<KullaniciRespons>()
    var error = MutableLiveData<Throwable>()


    private fun kullanicilariGetir() = viewModelScope.launch {
        kullaniciRepository.kullaniciGetir()
            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("mert", it.data.toString())
                        kullaniciLiveData.postValue(it.data!!)
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
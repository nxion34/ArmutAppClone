package com.ozyurt.armutapp.data.datasource


import com.ozyurt.armutapp.data.model.KullaniciRespons
import com.ozyurt.armutapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface KullaniciDataSouce {
    fun kullanicilariGetir(): Flow<Resource<KullaniciRespons>>
}
package com.ozyurt.armutapp.data.repository

import com.ozyurt.armutapp.data.datasource.KullaniciRemoteDataSource
import com.ozyurt.armutapp.data.model.KullaniciRespons
import com.ozyurt.armutapp.util.Resource
import kotlinx.coroutines.flow.Flow

class KullaniciRepository {
    private var kullaniciDataSouce: KullaniciRemoteDataSource?= null

    init {
        kullaniciDataSouce = KullaniciRemoteDataSource()
    }

    fun kullaniciGetir(): Flow<Resource<KullaniciRespons>>{
        return kullaniciDataSouce!!.kullanicilariGetir()
    }
}
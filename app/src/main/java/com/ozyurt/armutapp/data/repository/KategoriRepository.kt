package com.ozyurt.armutapp.data.repository

import com.ozyurt.armutapp.data.datasource.KategoriDataSource
import com.ozyurt.armutapp.data.datasource.KategoriRemoteDataSource
import com.ozyurt.armutapp.data.model.KategorilerUstalarRespons
import com.ozyurt.armutapp.util.Resource
import kotlinx.coroutines.flow.Flow

class KategoriRepository {
    private var kategoriDataSource: KategoriDataSource?=null

    init {
        kategoriDataSource=KategoriRemoteDataSource()
    }

    fun kategorileriGetir(): Flow<Resource<KategorilerUstalarRespons>>
    {
        return kategoriDataSource!!.kategorileriGetir()
    }
}
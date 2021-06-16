package com.ozyurt.armutapp.data.datasource

import com.ozyurt.armutapp.data.model.KategorilerUstalarRespons
import com.ozyurt.armutapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface KategoriDataSource {
    fun kategorileriGetir(): Flow<Resource<KategorilerUstalarRespons>>
}
package com.ozyurt.armutapp.data.datasource

import com.ozyurt.armutapp.data.model.KategorilerUstalarRespons
import com.ozyurt.armutapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class KategoriRemoteDataSource : KategoriDataSource {
    override fun kategorileriGetir(): Flow<Resource<KategorilerUstalarRespons>> = flow {
        try {
            emit(Resource.Loading())

            val response = KategoriService.build().kategorileriGetir()

            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }

}
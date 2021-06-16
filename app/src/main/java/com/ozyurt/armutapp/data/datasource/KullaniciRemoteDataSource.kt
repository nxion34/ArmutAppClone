package com.ozyurt.armutapp.data.datasource


import com.ozyurt.armutapp.data.model.KullaniciRespons
import com.ozyurt.armutapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class KullaniciRemoteDataSource: KullaniciDataSouce {
    override fun kullanicilariGetir(): Flow<Resource<KullaniciRespons>> = flow{
        try {
            emit(Resource.Loading())

            val response = KullaniciServis.build().kullaniciGetir()

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
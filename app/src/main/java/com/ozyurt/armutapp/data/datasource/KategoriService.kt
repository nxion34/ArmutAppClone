package com.ozyurt.armutapp.data.datasource

import com.ozyurt.armutapp.data.model.KategorilerUstalarRespons
import com.ozyurt.armutapp.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface KategoriService {
    @GET("hizmetler.json")
    suspend fun kategorileriGetir(): Response<KategorilerUstalarRespons>

    companion object {
        fun build(): KategoriService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .build()
            return retrofit.create(KategoriService::class.java)
        }
    }
}
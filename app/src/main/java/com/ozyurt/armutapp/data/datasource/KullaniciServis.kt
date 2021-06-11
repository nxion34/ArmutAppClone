package com.ozyurt.armutapp.data.datasource

import com.ozyurt.armutapp.data.model.KullaniciRespons
import com.ozyurt.armutapp.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface KullaniciServis {
    @GET("kullanicilar.json")
    suspend fun kullaniciGetir(): Response<KullaniciRespons>

    companion object{
        fun build(): KullaniciServis {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val  okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .build()
            return retrofit.create(KullaniciServis::class.java)
        }
    }
}
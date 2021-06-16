package com.ozyurt.armutapp.util
import com.google.gson.Gson

object NesneUtil {

        fun <T> nesnedenJsonStringe(objectData: T): String {
            val gson = Gson()
            return gson.toJson(objectData)
        }

        inline fun <reified T> jsonStringdenNesneye(jsonString: String): T {
            val gson = Gson()
            return gson.fromJson(jsonString, T::class.java)
        }

}
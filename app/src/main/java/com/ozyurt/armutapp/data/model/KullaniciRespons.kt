package com.ozyurt.armutapp.data.model

data class KullaniciRespons(
    val Kullanicilar: List<Kullanicilar>
)

data class Kullanicilar(
    val AdiSoyadi: String,
    val Email: String,
    val Sifre: String
)
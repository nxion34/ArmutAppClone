package com.ozyurt.armutapp.data.model

class KategorilerUstalarRespons : ArrayList<KategorilerUstalarResponsItem>()

data class KategorilerUstalarResponsItem(
    val KategoriAdi: String,
    val Ustalar: List<Ustalar>,
    val kapakResim: String
)

data class Ustalar(
    val Aciklamasi: String,
    val Fotografi: String,
    val Kategorisi: String,
    val MailAdresi: String,
    val Telefonu: String,
    val UstaAdi: String
)
package com.ozyurt.armutapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ozyurt.armutapp.R

object GlideUtil {
    fun resmiIndirGoster(context: Context?, url: String?, img: ImageView?) {
        Glide.with(context!!)
            .load(url!!)
            .centerCrop()
            .into(img!!)
    }
}

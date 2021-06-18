package com.ozyurt.armutapp.util

import android.app.ProgressDialog
import android.content.Context
import com.ozyurt.armutapp.R

object ProggresDialogUtil {
    var progressDialog: ProgressDialog? = null

    fun goster(context: Context, loadingTitle: String){
        progressDialog = ProgressDialog(context)

        progressDialog!!.apply {
            setTitle(loadingTitle)
            setMessage(context.getString(R.string.lutfenBekleyiniz))
            setCancelable(false)
            show()
        }
    }
    fun gizle(){
        progressDialog!!.run {
            this.dismiss()
        }
    }
}
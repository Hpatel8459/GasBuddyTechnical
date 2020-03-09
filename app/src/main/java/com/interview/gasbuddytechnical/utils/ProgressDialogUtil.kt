package com.interview.technicalround.utils

import android.app.ProgressDialog
import android.content.Context
import androidx.annotation.Nullable

object ProgressDialogUtil {

    var progressDialog: ProgressDialog? = null

    public fun showProgress(
        context: Context, @Nullable title: String, @Nullable message: String,
        cancelable: Boolean
    ) {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(context)
            }
            if (title.equals("")) {
                progressDialog!!.setTitle("Loading...");
            } else {
                progressDialog!!.setTitle(title);
            }
            if (message.equals("")) {
                progressDialog!!.setMessage("Please Wait");
            } else {
                progressDialog!!.setMessage(message);
            }
            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog!!.setCancelable(cancelable);
            progressDialog!!.show();
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }

    fun dismissProgress() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }


}
package com.interview.technicalround.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import com.interview.gasbuddytechnical.R


object CommonUtils {

    const val IMAGE_ID: String = ""
    const val BASE_URL = "https://api.unsplash.com/"
    const val CLIENT_ID = "fitLKaT3lVvv3jOHha2tYTw_AnG6_UE7BJjNg6rVIbY"

    fun isInternetAvailable(context: Context?): Boolean {
        if (context == null) {
            return false
        }
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        } else {
            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            } catch (e: java.lang.Exception) {
                Toast.makeText(
                    context,
                    "" + context.resources.getString(R.string.no_internet),
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        }
        Toast.makeText(
            context,
            "" + context.resources.getString(R.string.no_internet),
            Toast.LENGTH_SHORT
        ).show()
        return false
    }

}
package com.interview.gasbuddytechnical.ui.view.viewmodel

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.interview.gasbuddytechnical.R
import com.interview.technicalround.api.APIClient
import com.interview.technicalround.api.APIInterface
import com.interview.technicalround.ui.model.ImageModel
import com.interview.technicalround.utils.CommonUtils
import com.interview.technicalround.utils.ProgressDialogUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : BaseViewModel() {

    var imageModel = ObservableField<ImageModel>()

    companion object {
        @JvmStatic
        @BindingAdapter("url")
        fun loadImage(v: ImageView, d: String?) {
            Glide.with(v.context).load(d)
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_user)
                .into(v);
        }
    }


    fun loadImage(context: Context, id: String?) {
        if (CommonUtils.isInternetAvailable(context)) {
            ProgressDialogUtil.showProgress(context, "", "", false)

            val apiClient = APIClient.client?.create(APIInterface::class.java)

            val imagesCall = apiClient?.getImage(id.toString(), CommonUtils.CLIENT_ID)

            imagesCall?.enqueue(object : Callback<ImageModel> {
                override fun onFailure(call: Call<ImageModel>, t: Throwable) {
                    ProgressDialogUtil.dismissProgress()
                    Toast.makeText(context, R.string.str_something_went_worng, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<ImageModel>, response: Response<ImageModel>) {
                    ProgressDialogUtil.dismissProgress()
                    val imageModel = response.body()

                    if (imageModel != null) {
                        this@DetailsViewModel.imageModel.set(imageModel)
                        this@DetailsViewModel.toolbarTitle.set(imageModel.getUserName())
                    }
                }

            })
        }
    }

}
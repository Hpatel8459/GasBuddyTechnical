package com.interview.gasbuddytechnical.ui.view.viewmodel

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.interview.gasbuddytechnical.R
import com.interview.gasbuddytechnical.ui.view.adapters.ImageAdapter
import com.interview.technicalround.api.APIClient
import com.interview.technicalround.api.APIInterface
import com.interview.technicalround.ui.model.ImageModel
import com.interview.technicalround.utils.CommonUtils
import com.interview.technicalround.utils.ProgressDialogUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoViewModel() : BaseViewModel() {
    var images = ObservableArrayList<ImageModel>()
    var adapter = ImageAdapter(images)

    init {
        images.addOnListChangedCallback(object :
            ObservableList.OnListChangedCallback<ObservableArrayList<ImageModel>>() {
            override fun onChanged(sender: ObservableArrayList<ImageModel>?) {
            }

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<ImageModel>?,
                positionStart: Int,
                itemCount: Int
            ) {
                adapter.changeList()
            }

            override fun onItemRangeMoved(
                sender: ObservableArrayList<ImageModel>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                adapter.changeList()
            }

            override fun onItemRangeChanged(
                sender: ObservableArrayList<ImageModel>?,
                positionStart: Int,
                itemCount: Int
            ) {
                adapter.changeList()
            }

            override fun onItemRangeInserted(
                sender: ObservableArrayList<ImageModel>?,
                positionStart: Int,
                itemCount: Int
            ) {
                adapter.changeList()
            }
        })
    }

    fun loadImages(context: Context, page: String) {
        if (CommonUtils.isInternetAvailable(context)) {
            ProgressDialogUtil.showProgress(context, "", "", false)

            val apiClient = APIClient.client?.create(APIInterface::class.java)

            val imagesCall = apiClient?.getImages(CommonUtils.CLIENT_ID, page)

            imagesCall?.enqueue(object : Callback<ArrayList<ImageModel?>> {
                override fun onFailure(call: Call<ArrayList<ImageModel?>>, t: Throwable) {
                    ProgressDialogUtil.dismissProgress()
                    Toast.makeText(context, R.string.str_something_went_worng, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<ArrayList<ImageModel?>>,
                    response: Response<ArrayList<ImageModel?>>
                ) {
                    ProgressDialogUtil.dismissProgress()
                    val imageList = response.body()

                    if (imageList != null && imageList.size > 0) {
                        images.addAll(imageList)
                    }
                }

            })
        }
    }
}
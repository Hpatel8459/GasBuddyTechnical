package com.interview.technicalround.api

import com.interview.technicalround.ui.model.ImageModel
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("photos")
    fun getImages(@Query("client_id") clientId: String, @Query("page") page: String): Call<ArrayList<ImageModel?>>?

    @GET("photos/{id}")
    fun getImage(@Path("id") imageId: String, @Query("client_id") clientId: String): Call<ImageModel>?

}
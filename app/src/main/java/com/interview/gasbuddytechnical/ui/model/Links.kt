package com.interview.technicalround.ui.model

import com.google.gson.annotations.SerializedName

class Links {
    @SerializedName("download")
    var download: String? = null
    @SerializedName("download_location")
    var downloadLocation: String? = null
    @SerializedName("followers")
    var followers: String? = null
    @SerializedName("following")
    var following: String? = null
    @SerializedName("html")
    var html: String? = null
    @SerializedName("likes")
    var likes: String? = null
    @SerializedName("photos")
    var photos: String? = null
    @SerializedName("portfolio")
    var portfolio: String? = null
    @SerializedName("self")
    var self: String? = null

}
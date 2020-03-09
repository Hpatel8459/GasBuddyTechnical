package com.interview.technicalround.ui.model

import com.google.gson.annotations.SerializedName

class Urls {
    @SerializedName("full")
    var full: String? = null
    @SerializedName("raw")
    var raw: String? = null
    @SerializedName("regular")
    var regular: String? = null
    @SerializedName("small")
    var small: String? = null
    @SerializedName("thumb")
    var thumb: String? = null

}
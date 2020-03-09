package com.interview.technicalround.ui.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("first_name")
    var firstName: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("instagram_username")
    var instagramUsername: String? = null
    @SerializedName("last_name")
    var lastName: Any? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("username")
    var username: String? = null

}
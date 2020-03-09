package com.interview.technicalround.ui.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.interview.gasbuddytechnical.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ImageModel {

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

    @SerializedName("created_at")
    private var mCreatedAt: String? = null
    @SerializedName("description")
    private var mDescription: Any? = null
    @SerializedName("id")
    private var mId: String? = null
    @SerializedName("likes")
    private var mLikes: String? = null
    @SerializedName("links")
    private var mLinks: Links? = null
    @SerializedName("promoted_at")
    private var mPromotedAt: Any? = null
    @SerializedName("updated_at")
    private var mUpdatedAt: String? = null
    @SerializedName("urls")
    private var mUrls: Urls? = null
    @SerializedName("user")
    private var mUser: User? = null

    fun getUrl(): String? {
        return getUrls()!!.regular
    }

    fun getCreatedAt(): String? {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.CANADA)
        val date: Date = dateFormat.parse(mCreatedAt!!)!!
        return dateFormat.format(date)
    }

    fun setCreatedAt(createdAt: String?) {
        mCreatedAt = createdAt
    }

    fun getDescription(): Any? {
        return mDescription
    }

    fun setDescription(description: Any?) {
        mDescription = description
    }

    fun getId(): String? {
        return mId
    }

    fun setId(id: String?) {
        mId = id
    }

    fun getLikes(): String? {
        return mLikes
    }

    fun setLikes(likes: String?) {
        mLikes = likes
    }

    fun getLinks(): Links? {
        return mLinks
    }

    fun setLinks(links: Links?) {
        mLinks = links
    }

    fun getPromotedAt(): Any? {
        return mPromotedAt
    }

    fun setPromotedAt(promotedAt: Any?) {
        mPromotedAt = promotedAt
    }

    fun getUpdatedAt(): String? {
        return mUpdatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        mUpdatedAt = updatedAt
    }

    fun getUrls(): Urls? {
        return mUrls
    }

    fun setUrls(urls: Urls?) {
        mUrls = urls
    }

    fun getUser(): User? {
        return mUser
    }

    fun setUser(user: User?) {
        mUser = user
    }

    fun getUserName(): String? {
        return getUser()?.name
    }

}
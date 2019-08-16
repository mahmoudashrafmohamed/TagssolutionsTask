package com.dev.mahmoud_ashraf.tagssolutionstask.models
import com.google.gson.annotations.SerializedName


data class mLogin(
    @SerializedName("Address")
    val address: String = "",
    @SerializedName("Email")
    val email: String = "",
    @SerializedName("Full_Name")
    val fullName: String = "",
    @SerializedName("IsAdded")
    val isAdded: Int = 0,
    @SerializedName("Message")
    val message: String = "",
    @SerializedName("Phone")
    val phone: String = "",
    @SerializedName("PicPathb")
    val picPathb: String = "",
    @SerializedName("token")
    val token: String = "",
    @SerializedName("UserID")
    val userID: Int = 0,
    @SerializedName("UserType")
    val userType: String = ""
)
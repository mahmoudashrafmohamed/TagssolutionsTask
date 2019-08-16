package com.dev.mahmoud_ashraf.tagssolutionstask.models
import com.google.gson.annotations.SerializedName


data class mSplashSteps(
    @SerializedName("HasTool")
    val hasTool: Int = 0,
    @SerializedName("ToolData")
    val toolData: MutableList<ToolData?> = mutableListOf()
) {
    data class ToolData(
        @SerializedName("PicPath")
        val picPath: String = "",
        @SerializedName("Title")
        val title: String = ""
    )
}
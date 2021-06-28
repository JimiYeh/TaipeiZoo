package com.cloudinteractive.taipeizoo.model.area


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetAreaListResp(
    @SerializedName("result")
    val result: Result = Result()
) {
    data class Result(
        @SerializedName("count")
        val count: Int = 0,
        @SerializedName("limit")
        val limit: Int = 0,
        @SerializedName("offset")
        val offset: Int = 0,
        @SerializedName("results")
        val areas: List<Area> = listOf(),
        @SerializedName("sort")
        val sort: String = ""
    ) {
        @Parcelize
        data class Area(
            @SerializedName("E_Category")
            val eCategory: String = "",
            @SerializedName("E_Geo")
            val eGeo: String = "",
            @SerializedName("E_Info")
            val eInfo: String = "",
            @SerializedName("E_Memo")
            val eMemo: String = "",
            @SerializedName("E_Name")
            val eName: String = "",
            @SerializedName("E_no")
            val eNo: String = "",
            @SerializedName("E_Pic_URL")
            val ePicURL: String = "",
            @SerializedName("E_URL")
            val eURL: String = "",
            @SerializedName("_id")
            val id: Int = 0
        ) : Parcelable
    }
}
package com.letsmovie.model

import com.google.gson.annotations.SerializedName

data class DataListResponse<out T>(
    @SerializedName("page")
    val currentPage: Int?,
    @SerializedName("results")
    val dataList: List<T>,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

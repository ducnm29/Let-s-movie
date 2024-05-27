package com.letsmovie.data.network.movie

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

fun <T, U> DataListResponse<T>.toModel(parse: (List<T>) -> List<U>): DataListResponse<U> {
    return DataListResponse(
        currentPage = currentPage,
        dataList = parse(dataList),
        totalPages = totalPages,
        totalResults = totalResults
    )
}

package com.letsmovie.data.api

import com.letsmovie.util.Define
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkManager {
    fun createMovieApi(): MovieApi = createRetrofit().create(MovieApi::class.java)
    fun createTvApi(): TvApi = createRetrofit().create(TvApi::class.java)
    fun createGenreApi(): GenreApi = createRetrofit().create(GenreApi::class.java)
    fun createCastApi(): CastApi = createRetrofit().create(CastApi::class.java)
    private fun createRetrofit(): Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl(Define.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
        return retrofit.build()
    }
    private fun createOkHttpClient() : OkHttpClient{
        val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
        }
        return okHttpClient.build()
    }

}
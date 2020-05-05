package com.oladiniabayomi.digitalnews.network

import retrofit2.http.GET
import retrofit2.Call

interface AdsWebService {
    @GET("?app_id=18581")
    fun getValue(): Call<String>
}
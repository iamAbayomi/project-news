package com.oladiniabayomi.digitalnews.network


import com.oladiniabayomi.digitalarticles.articles.Articles
import retrofit2.Call
import retrofit2.http.GET

interface PostsService {
    @GET("wp-json/wp/v2/posts?per_page=30")
    fun getPosts(): Call<List<Articles>>
}
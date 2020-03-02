package com.oladiniabayomi.digitalnews.network


import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalnews.Posts
import retrofit2.Call
import retrofit2.http.GET

interface PostsService {
    @GET("wp-json/wp/v2/posts")
    fun getPosts(): Call<List<Articles>>
}
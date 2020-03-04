package com.oladiniabayomi.digitalnews.repository

import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.articles.ArticlesDao
import com.oladiniabayomi.digitalnews.network.PostsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ArticleRepository( private val articlesDao: ArticlesDao){

   // private val webSer

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    val allArticles : LiveData<List<Articles>> = articlesDao.getAllArticles()


    suspend fun insert(articles: Articles){
        articlesDao.insertArticles(articles)
    }

    private fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.tell.com.ng/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PostsService::class.java)
        val call = service.getPosts()

        val data = MutableLiveData<List<Articles>>()

        call.enqueue(object : Callback<List<Articles>> {

            override fun onResponse(call: Call<List<Articles>>?, response: Response<List<Articles>>?) {
                if (response!!.code() == 200)
                {

                    data.value = response.body()

                //   currentArticles.addAll(response.body())
                //  homeViewModel.insert(currentArticles[1])
                //Adding Articles to database

                for (x in 0 until currentArticles.size) {
                    //  homeViewModel.insert(currentArticles[x])

                }

                //Clearing Fragments From Database
                // fragments.clear()

                for ( x in 0..5 ){
                    //  fragments.add(FeaturedFragment().newInstance(currentArticles[x].articlesThumbnailImage!!,
                    //    currentArticles[x].articlesTitle!!.rendered!!))
                }

                //      mAdapter!!.notifyDataSetChanged()
                //  articlesRecyclerViewAdapter!!.notifyDataSetChanged()
            } }


            override fun onFailure(call: Call<List<Articles>>?, t: Throwable?) {
                //   textView!!.text = t!!.message
            }
        })
    }
}
package com.oladiniabayomi.digitalnews.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalnews.SharedPreferencesHelper
import com.oladiniabayomi.digitalnews.articles.ArticlesDao
import com.oladiniabayomi.digitalnews.articles.Instantiate
import com.oladiniabayomi.digitalnews.network.PostsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ArticleRepository( private val articlesDao: ArticlesDao, var context: Application){

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
    var currentArticles = ArrayList<Articles>()

    val allArticles : LiveData<List<Articles>> = getArticles()
    var isInitialize : Boolean = false
    val FRESH_TIMEOUT_IN_MINUTES = 2

    var currentTime = getTime()

    //val mainHandler =Hanlder)

    suspend fun insert(articles: Articles){
        articlesDao.insertArticles(articles)
    }

    fun  getArticles() : LiveData<List<Articles>> {
        var sharedPreferences :SharedPreferencesHelper = SharedPreferencesHelper()

        return if (sharedPreferences.getSharedPrefs(context) != "sent" )
        {
            Toast.makeText(context, "I am here", Toast.LENGTH_LONG).show()
            sharedPreferences.saveSharedPrefs(context)

            val sdf = SimpleDateFormat("mm")
            val currentDate = sdf.format(Date())
            var instantiate = Instantiate(currentDate.toInt())

            coroutineScope.launch(Dispatchers.Main) {
                articlesDao.instatiate(instantiate)
            }

            refreshArticles()
        }
        else{

            refreshArticles()

          return  articlesDao.getAllArticles()

        }
    }
     //Call for Retrofit
     fun refreshArticles() : LiveData<List<Articles>> {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.tell.com.ng/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PostsService::class.java)
        val call = service.getPosts()
        var data = MutableLiveData<List<Articles>>()

         var dat = MutableLiveData<List<Articles>>()


         val sdf = SimpleDateFormat("mm")
         val currentDate = sdf.format(Date()).toInt()

         var time = 2

         coroutineScope.launch {
            time = articlesDao.lastTime()
         }.invokeOnCompletion {


             if (abs((currentDate - time)) > FRESH_TIMEOUT_IN_MINUTES) {
                 Toast.makeText(context, abs((time)).toString(), Toast.LENGTH_LONG)
                     .show()
/*
             Toast.makeText(context, abs( ( currentDate)).toString() , Toast.LENGTH_LONG)
                 .show()*/

                 call.enqueue(object : Callback<List<Articles>> {
                     override fun onResponse(
                         call: Call<List<Articles>>?,
                         response: Response<List<Articles>>?
                     ) {
                         if (response!!.code() == 200) {
                             data.value = response.body()
                             currentArticles.addAll(response.body())
                             Toast.makeText(
                                 context,
                                 response.body()[1].toString(),
                                 Toast.LENGTH_LONG
                             )
                                 .show()

                             val sdf = SimpleDateFormat("mm")
                             val currentDate = sdf.format(Date())


                             var instantiate = Instantiate(currentDate.toInt())

                             coroutineScope.launch(Dispatchers.Main) {
                                 articlesDao.deleteAll()
                                 articlesDao.reInstatiate(instantiate)
                             }

                             for (x in 0 until currentArticles.size) {
                                 coroutineScope.launch(Dispatchers.Main) {
                                     articlesDao.insertArticles(currentArticles[x])
                                 }
                             }
                         }
                     }

                     override fun onFailure(call: Call<List<Articles>>?, t: Throwable?) {
                     }
                 })
             }
         }

        return  data
    }


    fun getTime(): Int{

        val sdf = SimpleDateFormat("mm")
        val currentDate = sdf.format(Date())

        return  currentDate.toInt()
    }

}
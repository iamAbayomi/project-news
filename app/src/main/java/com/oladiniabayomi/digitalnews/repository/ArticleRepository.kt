package com.oladiniabayomi.digitalnews.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalnews.articles.ArticlesDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ArticleRepository( private val articlesDao: ArticlesDao){

   // private val webSer

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    val allArticles : LiveData<ArrayList<Articles>> = articlesDao.getAllArticles()


    suspend fun insert(articles: Articles){
        articlesDao.insertArticles(articles)
    }

}
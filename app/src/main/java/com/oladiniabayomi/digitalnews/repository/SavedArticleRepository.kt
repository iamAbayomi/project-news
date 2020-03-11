package com.oladiniabayomi.digitalnews.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalarticles.articles.Title
import com.oladiniabayomi.digitalnews.saved_articles.SavedArticlesDao

class SavedArticleRepository(private val savedArticlesDao: SavedArticlesDao, var context: Application) {


    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    val allSaveArticles: LiveData<List<SavedArticles>> = savedArticlesDao.getAllSavedArticles()
   // val isArticlesPesent : LiveData<Int> = savedArticlesDao.isArticlesPesent(allSaveArticles)


    fun isArticlePresent(title: String): LiveData<Int>{

      return  savedArticlesDao.isArticlesPesent(title)
    }

    suspend fun insertIntoSaved(savedArticles: SavedArticles) {
        savedArticlesDao.insertSaved(savedArticles)
    }

    fun getSingleSavedArticle(title: String) : LiveData<SavedArticles> {
       return savedArticlesDao.getSingleSavedArticles(title)
    }

    suspend fun deleteArticles(title: String){

        savedArticlesDao.deletesavedArticles(title)
    }

}



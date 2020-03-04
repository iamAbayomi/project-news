package com.oladiniabayomi.digitalnews.repository

import androidx.lifecycle.LiveData
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.saved_articles.SavedArticlesDao

class SavedArticleRepository(private val savedArticlesDao: SavedArticlesDao) {


    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    val allSaveArticles : LiveData<List<SavedArticles>> = savedArticlesDao.getAllSavedArticles()


    suspend fun insertIntoSaved(savedArticles: SavedArticles){
        savedArticlesDao.insertSaved(savedArticles)
    }
}


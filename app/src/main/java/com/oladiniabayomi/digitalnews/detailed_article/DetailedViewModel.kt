package com.oladiniabayomi.digitalnews.detailed_article

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.articles.ArticlesRoomDatabase
import com.oladiniabayomi.digitalnews.repository.SavedArticleRepository
import kotlinx.coroutines.launch

class DetailedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SavedArticleRepository

    init {
        val savedArticlesDao = ArticlesRoomDatabase.getDatabase(application, viewModelScope).savedArticlesDao()
        repository = SavedArticleRepository(savedArticlesDao, application)

    }


    fun insertArticles(savedArticles: SavedArticles) = viewModelScope.launch{
        repository.insertIntoSaved(savedArticles)
    }

    fun isArticlesPresent(title: String): LiveData<Int>{
        return repository.isArticlePresent(title)
    }

    fun getSingleArticle(title : String) : LiveData<SavedArticles> {

        return repository.getSingleSavedArticle(title)
    }


     fun deleteArticles(title: String) = viewModelScope.launch {
        repository.deleteArticles(title)

    }

}
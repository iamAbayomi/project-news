package com.oladiniabayomi.digitalnews.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalnews.articles.ArticlesDao
import com.oladiniabayomi.digitalnews.articles.ArticlesRoomDatabase
import com.oladiniabayomi.digitalnews.repository.ArticleRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    // The ViewModel maintains a reference to the repository to get data.
    private  val repository: ArticleRepository
    // LiveData gives us updated words when they change.
    val allArticles : LiveData<ArrayList<Articles>>

    init {
        val articlesDao = ArticlesRoomDatabase.getDatabase(application).articlesDao()
        repository = ArticleRepository(articlesDao)
        allArticles = repository.allArticles
    }


    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(articles: Articles) = viewModelScope.launch {
        repository.insert(articles)
    }
}
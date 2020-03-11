package com.oladiniabayomi.digitalnews.saved_articles

import android.app.Application
import androidx.lifecycle.*
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.articles.ArticlesRoomDatabase
import com.oladiniabayomi.digitalnews.repository.SavedArticleRepository

class SavedArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


    private val repository : SavedArticleRepository

    val savedArticles : LiveData<List<SavedArticles>>

    init {

        val savedArticlesDao = ArticlesRoomDatabase.getDatabase(application, viewModelScope).savedArticlesDao()
        repository = SavedArticleRepository(savedArticlesDao, application)
        savedArticles = repository.allSaveArticles
    }




}
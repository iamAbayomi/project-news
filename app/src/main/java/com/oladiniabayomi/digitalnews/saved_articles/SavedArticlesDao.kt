package com.oladiniabayomi.digitalnews.saved_articles

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oladiniabayomi.digitalarticles.articles.SavedArticles

@Dao
interface SavedArticlesDao {

    @Insert
    suspend fun insertSaved( vararg savedArticles: SavedArticles)

    @Query("SELECT * FROM saved_table WHERE title = :articlesTitle")
    fun getSingleSavedArticles(articlesTitle: String) : LiveData<SavedArticles>



    @Query("SELECT * FROM saved_table")
    fun getAllSavedArticles() : LiveData<List<SavedArticles>>

    @Query("DELETE FROM saved_table WHERE title = :articlesTitle")
    suspend fun deletesavedArticles(articlesTitle : String)



}
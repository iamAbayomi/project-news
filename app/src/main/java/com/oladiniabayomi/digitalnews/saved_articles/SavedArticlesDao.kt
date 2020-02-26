package com.oladiniabayomi.digitalnews.saved_articles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SavedArticlesDao {
    @Insert
    fun insertSavedArticles( vararg savedArticles: SavedArticles)

    @Query("SELECT * FROM saved_table")
    fun getAllSavedArticles(): List<SavedArticles>


}
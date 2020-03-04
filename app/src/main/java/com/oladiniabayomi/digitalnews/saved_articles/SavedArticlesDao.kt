package com.oladiniabayomi.digitalnews.saved_articles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oladiniabayomi.digitalarticles.articles.Articles

@Dao
interface SavedArticlesDao {
    @Insert
    fun insertArticles( vararg savedArticles: Articles)

    @Query("SELECT * FROM articles_table")
    fun getAllArticles(): List<Articles>


}
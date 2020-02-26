package com.oladiniabayomi.digitalnews.articles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.oladiniabayomi.digitalarticles.articles.Articles

@Dao
interface ArticlesDao {
    @Insert
    fun insertArticles( vararg articles: Articles)

    @Query("SELECT * FROM articles_table")
    fun getAllArticles() : List<Articles>

}
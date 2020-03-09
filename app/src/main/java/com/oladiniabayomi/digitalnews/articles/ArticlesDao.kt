package com.oladiniabayomi.digitalnews.articles

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.oladiniabayomi.digitalarticles.articles.Articles

@Dao
interface ArticlesDao {

    @Insert
    suspend fun insertArticles( vararg articles: Articles)


    @Query("SELECT * FROM articles_table")
    fun getAllArticles() : LiveData<List<Articles>>


    @Insert
    suspend fun instatiate( instantiate: Instantiate )

    @Update
    suspend fun reInstatiate( instantiate: Instantiate )

    @Query("Select instantiated from instantiate ")
    suspend fun isInstatiate(): Boolean

    @Query("DELETE  FROM articles_table")
    suspend fun deleteAll()


}
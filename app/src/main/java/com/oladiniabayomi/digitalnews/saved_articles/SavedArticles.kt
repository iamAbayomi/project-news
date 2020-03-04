package com.oladiniabayomi.digitalarticles.articles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.oladiniabayomi.digitalnews.content.Content
import java.io.Serializable
import java.util.*

@Entity(tableName = "saved_table")
class SavedArticles : Serializable {


   // @SerializedName("id")
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    var articlesId: Int? = null

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var articlesTitle: Title? = null


    @SerializedName("content")
    @ColumnInfo(name = "content")
    var articlesFullText: Content? = null


    @SerializedName("jetpack_featured_media_url")
    @ColumnInfo(name = "articles_thumbnail")
    var articlesThumbnailImage: String? = null


    @SerializedName("date")
    @ColumnInfo(name = "articles_timestamp")
    var articlesTimeStamp: String? = null




}


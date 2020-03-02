package com.oladiniabayomi.digitalarticles.articles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.oladiniabayomi.digitalnews.content.Content
import java.util.*

@Entity(tableName = "articles_table")
class Articles {


    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "articles_id")
    val articlesId: Int? = null

    @SerializedName("title")
    @ColumnInfo(name = "articles_title")
    var articlesTitle: Title? = null


    @SerializedName("content")
    @ColumnInfo(name = "articles_fulltext")
    var articlesFullText: Content? = null


    @SerializedName("jetpack_featured_media_url")
    @ColumnInfo(name = "articles_thumbnail")
    var articlesThumbnailImage: String? = null


    @ColumnInfo(name = "articles_imageUrl")
    var articlesImage: String? = null


    @SerializedName("date")
    @ColumnInfo(name = "articles_timestamp")
    var articlesTimeStamp: String? = null




}


class Title{

    @SerializedName("rendered")
    var rendered : String? = null


}
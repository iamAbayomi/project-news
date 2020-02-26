package com.oladiniabayomi.digitalarticles.articles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "articles_table")
class Articles (

    @PrimaryKey @ColumnInfo(name = "articles_id")
    val articlesId: Int,

    @ColumnInfo( name = "articles_title")
    var articlesTitle: String,

    @ColumnInfo (name = "articles_fulltext")
    var articlesFullText: String,

    @ColumnInfo(name = "articles_thumbnail")
    var articlesThumbnailImage: String,

    @ColumnInfo(name ="articles_imageUrl")
    var articlesImage : String,

    @ColumnInfo(name = "articles_timestamp")
    var articlesTimeStamp : Date
)
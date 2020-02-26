package com.oladiniabayomi.digitalnews.saved_articles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "saved_table")
class SavedArticles (

    @PrimaryKey @ColumnInfo( name = "saved_id")
    val savedId : String,

    @ColumnInfo( name = "news_title")
    var newsTitle: String,

    @ColumnInfo (name = "news_fulltext")
    var newsFullText: String,

    @ColumnInfo(name = "news_thumbnail")
    var newsThumbnailImage: String,

    @ColumnInfo(name ="news_ImageUrl")
    var newsImage : String,

    @ColumnInfo(name = "time_stamp")
    var timeStamp : Date

)
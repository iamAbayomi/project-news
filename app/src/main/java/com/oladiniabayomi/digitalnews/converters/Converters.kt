package com.oladiniabayomi.digitalnews.converters

import android.service.quicksettings.Tile
import androidx.room.TypeConverter
import com.oladiniabayomi.digitalarticles.articles.Title
import com.oladiniabayomi.digitalnews.content.Content

class Converters {

    companion object{

        @TypeConverter
        @JvmStatic
        fun fromTitle(value: Title ) : String?{
           return  value.rendered
        }


        @TypeConverter
        @JvmStatic
        fun toTitle(value: String): Title {

            return Title(value)
        }

        @TypeConverter
        @JvmStatic
        fun fromContent(value: Content) : String?{
           return  value.rendered
        }


        @TypeConverter
        @JvmStatic
        fun toContent(value: String): Content {

            return Content(value)
        }

    }

}
package com.oladiniabayomi.digitalnews.articles

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.converters.Converters
import com.oladiniabayomi.digitalnews.saved_articles.SavedArticlesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Articles::class, SavedArticles::class, Instantiate::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticlesRoomDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao
    abstract fun savedArticlesDao() : SavedArticlesDao


    companion object {
        @Volatile
        private var INSTANCE: ArticlesRoomDatabase? = null

        fun getDatabase(context: Context,
                scope: CoroutineScope):
                ArticlesRoomDatabase {


          return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticlesRoomDatabase::class.java,
                    "articles_database"
                ).addCallback(ArticlesDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                 instance
            }


        }

        private class ArticlesDatabaseCallback(private val scope: CoroutineScope)
            : RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch (Dispatchers.IO) {
                        populateDatabase(database.articlesDao())
                    }
                }

            }

        }

        /*
        *  Populate db when db created
        * */
        suspend fun populateDatabase(articlesDao: ArticlesDao) {

            var instatiate = Instantiate()
         //   instatiate.instantiate = true
           // articlesDao.instatiate(instatiate)

        }


    }

}
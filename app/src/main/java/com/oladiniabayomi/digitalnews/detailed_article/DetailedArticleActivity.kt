package com.oladiniabayomi.digitalnews.detailed_article

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.R
import kotlinx.android.synthetic.main.activity_detailed_article.*
import org.jsoup.Jsoup

class DetailedArticleActivity : AppCompatActivity() {

    lateinit var saved_icon : ImageView

    private lateinit var detailedViewModel: DetailedViewModel
    var state = false

    lateinit var  int : String


   // var  tk : Int = 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_article)

        detailedViewModel = ViewModelProvider(this).get(DetailedViewModel::class.java)

       var post = intent.getStringExtra("post")

        var articles: Articles = intent.getSerializableExtra("articles") as Articles

       //if ( post == "article" )

       saved_icon = findViewById(R.id.saved_icon)
       //Set html to string in android
        text_title.text = HtmlCompat.fromHtml(articles.articlesTitle!!.rendered.toString(),0)
        text_content.text = HtmlCompat.fromHtml((articles.articlesFullText!!.rendered.toString()), 0)

        Glide.with(this)
            .load(articles.articlesThumbnailImage)
            .into(text_imageView)

       var unchecked: Drawable


       var savedArticles : SavedArticles = convertoSavedArticles(articles)



       detailedViewModel.isArticlesPresent(articles.articlesTitle!!.rendered!!)
           .observe(this, Observer { it ->
               var  tk : Int  = it
               int = it.toString()
               unchecked = if (tk.toString().equals("0")) {
                   resources.getDrawable(R.drawable.ic_saved_plain)
               } else {
                   resources.getDrawable(R.drawable.ic_saved_colored)
               }

               Glide.with(this)
                   .load(unchecked)
                   .into(saved_icon)

              // Toast.makeText(this, int.toString(), Toast.LENGTH_LONG ).show()

           })

       saved_icon.setOnClickListener {
           if (int == "0") {
               detailedViewModel.insertArticles(savedArticles)

           } else {
               detailedViewModel.deleteArticles(savedArticles.articlesTitle!!.rendered!!)
           }
       }

   }

    fun setState(){

    }

    fun convertoSavedArticles(articles: Articles): SavedArticles{

        var savedArticles = SavedArticles()

        savedArticles.articlesId = articles.articlesId
        savedArticles.articlesTitle = articles.articlesTitle
        savedArticles.articlesFullText = articles.articlesFullText
        savedArticles.articlesThumbnailImage = articles.articlesThumbnailImage
        savedArticles.articlesTimeStamp = articles.articlesTimeStamp


        return savedArticles
    }


}

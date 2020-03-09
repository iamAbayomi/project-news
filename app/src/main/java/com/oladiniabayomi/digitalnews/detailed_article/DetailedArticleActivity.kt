package com.oladiniabayomi.digitalnews.detailed_article

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
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

        var articles: Articles = intent.getSerializableExtra("articles") as Articles

       var savedArticles : SavedArticles = intent.getSerializableExtra("articles") as SavedArticles


       saved_icon = findViewById(R.id.saved_icon)

        text_title.text = articles.articlesTitle!!.rendered
        text_content.text = Jsoup.parse(articles.articlesFullText!!.rendered).text()

        Glide.with(this)
            .load(articles.articlesThumbnailImage)
            .into(text_imageView)

       var unchecked: Drawable


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

               Toast.makeText(this, tk.toString(), Toast.LENGTH_LONG ).show()

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

}

/*

var unchecked: Drawable
saved_icon.setOnClickListener {
    if (!state) {
        unchecked = resources.getDrawable(R.drawable.ic_saved_plain)
        state = true
    } else {
        unchecked = resources.getDrawable(R.drawable.ic_saved_colored)
        state = false
    }
    Glide.with(this)
        .load(unchecked)
        .into(saved_icon)
    //  detailedViewModel.

}*/

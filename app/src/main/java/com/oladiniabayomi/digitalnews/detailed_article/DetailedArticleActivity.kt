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
import com.oladiniabayomi.digitalnews.R
import kotlinx.android.synthetic.main.activity_detailed_article.*
import org.jsoup.Jsoup

class DetailedArticleActivity : AppCompatActivity() {

    lateinit var saved_icon : ImageView

    private lateinit var detailedViewModel: DetailedViewModel
    var state = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_article)

        detailedViewModel = ViewModelProvider(this).get(DetailedViewModel::class.java)

        var articles: Articles = intent.getSerializableExtra("articles") as Articles

        saved_icon = findViewById(R.id.saved_icon)

        text_title.text = articles.articlesTitle!!.rendered
        text_content.text = Jsoup.parse(articles.articlesFullText!!.rendered).text()

        Glide.with(this)
            .load(articles.articlesThumbnailImage)
            .into(text_imageView)

        //articles.articlesTitle!!.rendered?.let { detailedViewModel.getSingleArticle(it)}

       var  tk : String = detailedViewModel.getSingleArticle(articles.articlesTitle!!.rendered!!).toString()

        Toast.makeText(this, tk, Toast.LENGTH_LONG ).show()




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

package com.oladiniabayomi.digitalnews.detailed_article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalnews.R
import kotlinx.android.synthetic.main.activity_detailed_article.*
import org.jsoup.Jsoup

class DetailedArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_article)

        var articles : Articles = intent.getSerializableExtra("articles") as Articles


        text_title.text = articles.articlesTitle!!.rendered

        text_content.text = Jsoup.parse(articles.articlesFullText!!.rendered).text()



        Glide.with(this)
            .load(articles.articlesThumbnailImage)
            .into(text_imageView)



    }


}

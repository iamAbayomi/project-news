package com.oladiniabayomi.digitalnews.saved_articles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.articles.ArticlesRecyclerViewAdapter
import com.oladiniabayomi.digitalnews.detailed_article.DetailedArticleActivity
import com.oladiniabayomi.digitalnews.interfaces.SavedOnItemClickListener

class SavedArticlesFragment : Fragment(), SavedOnItemClickListener {


    private lateinit var savedArticlesViewModel: SavedArticlesViewModel

    lateinit var recyclerView: RecyclerView
    private var articlesRecyclerViewAdapter: SavedRecyclerViewAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var currentArticles = ArrayList<SavedArticles>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedArticlesViewModel =
            ViewModelProvider(this).get(SavedArticlesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_saved, container, false)
        //Set Recycler View adapter
        recyclerView = root.findViewById(R.id.saved_recyclerView)
        layoutManager = LinearLayoutManager(activity)

        articlesRecyclerViewAdapter =
            context?.let { SavedRecyclerViewAdapter(it, currentArticles, this) }
        //setting Recycler Views attribute
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = articlesRecyclerViewAdapter

        savedArticlesViewModel.savedArticles.observe(viewLifecycleOwner, Observer { saveArticles ->
            saveArticles.let { articlesRecyclerViewAdapter!!.setArticles(ArrayList(it)) }

        })

        return root
    }

    override fun onItemClick(articles: SavedArticles) {
        val intent = Intent(activity, DetailedArticleActivity::class.java)
        //Converting Articles to Saved Articles
        var articless :Articles = convertArticles(articles)

        intent.putExtra("articles", articless)

        startActivity(intent)

      }


    fun convertArticles(articles: SavedArticles): Articles{

        var savedArticles = Articles()

        savedArticles.articlesId = articles.articlesId
        savedArticles.articlesTitle = articles.articlesTitle
        savedArticles.articlesFullText = articles.articlesFullText
        savedArticles.articlesThumbnailImage = articles.articlesThumbnailImage
        savedArticles.articlesTimeStamp = articles.articlesTimeStamp


        return savedArticles
    }


}
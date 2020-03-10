package com.oladiniabayomi.digitalnews.saved_articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.articles.ArticlesRecyclerViewAdapter
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
        recyclerView = root.findViewById(R.id.saved_recyclerView)
        layoutManager = LinearLayoutManager(activity)

        articlesRecyclerViewAdapter =
            context?.let { SavedRecyclerViewAdapter(it, currentArticles, this) }
        //setting Recycler Views attribute
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = articlesRecyclerViewAdapter



        return root
    }

    override fun onItemClick(articles: SavedArticles) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
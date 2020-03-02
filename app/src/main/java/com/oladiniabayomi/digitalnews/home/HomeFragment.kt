package com.oladiniabayomi.digitalnews.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.articles.ArticlesRecyclerViewAdapter
import com.oladiniabayomi.digitalnews.detailed_article.DetailedArticleActivity
import com.oladiniabayomi.digitalnews.network.PostsService
import kotlinx.android.synthetic.main.fragment_home.*
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    //RecyclerView Variables
    lateinit var recyclerView: RecyclerView
    private var  articlesRecyclerViewAdapter : ArticlesRecyclerViewAdapter? = null
    private var layoutManager: LayoutManager? = null

    private var currentArticles  = ArrayList<Articles>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val textView = root.findViewById<TextView>(R.id.textView)



        recyclerView = root.findViewById(R.id.home_recyclerview)
        layoutManager = LinearLayoutManager(activity)

        articlesRecyclerViewAdapter =
            context?.let { ArticlesRecyclerViewAdapter(it, currentArticles) }


        recyclerView.layoutManager = layoutManager
        recyclerView.adapter =  articlesRecyclerViewAdapter

        getCurrentData()



        textView.setOnClickListener {
            val intent = Intent( activity , DetailedArticleActivity::class.java)
            startActivity(intent)
        }


        articlesRecyclerViewAdapter!!.notifyDataSetChanged()

        return root
    }

    private fun getCurrentData() {
        val retrofit =  Retrofit.Builder()
            .baseUrl("http://www.tell.com.ng/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PostsService::class.java)
        val call = service.getPosts()

        call.enqueue( object : Callback<List<Articles>> {
            override fun onResponse(call: Call<List<Articles>>?, response: Response<List<Articles>>?) {
                if(response!!.code() == 200){

                    currentArticles.addAll(response.body())

                    val  postsResponse = response.body()[1]

                    val stringBuilder = "int " + postsResponse.articlesTitle + "\n" +
                            "date "  + postsResponse.articlesTimeStamp + "\n" +
                            "content \n" + postsResponse.articlesFullText!!.rendered

                   textView!!.text = Jsoup.parse(stringBuilder).text()


                }
            }

            override fun onFailure(call: Call<List<Articles>>?, t: Throwable?) {
                textView!!.text = t!!.message
            }


        })

    }
}
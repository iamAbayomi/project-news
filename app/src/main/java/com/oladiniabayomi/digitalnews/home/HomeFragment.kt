package com.oladiniabayomi.digitalnews.home

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager.widget.ViewPager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalnews.FragmentViewPager
import com.oladiniabayomi.digitalnews.MyPageIndicator
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.articles.ArticlesRecyclerViewAdapter
import com.oladiniabayomi.digitalnews.detailed_article.DetailedArticleActivity
import com.oladiniabayomi.digitalnews.featured.FeaturedFragment
import com.oladiniabayomi.digitalnews.interfaces.OnItemClickListener
import com.oladiniabayomi.digitalnews.network.PostsService
import kotlinx.android.synthetic.main.fragment_home.*
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class HomeFragment : Fragment(), OnItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    //RecyclerView Variables
    lateinit var recyclerView: RecyclerView
    private var articlesRecyclerViewAdapter: ArticlesRecyclerViewAdapter? = null
    private var layoutManager: LayoutManager? = null
    private var currentArticles = ArrayList<Articles>()

    //ViewPager variables
    var fragments = ArrayList<Fragment>()
    private var viewPager: ViewPager? = null
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Initializing views in fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //recyclerview Initialization
        initialization(root)

        //observing articles in Recycler View in android
        homeViewModel.allArticles.observe(viewLifecycleOwner, Observer { articles ->
            articles.let { articlesRecyclerViewAdapter!!.setArticles(ArrayList(it)) }
        })

        //Checking Internet
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        //Skeleton Views
        //skeleton.showSkeleton()
        homeViewModel.allCategories.observe(viewLifecycleOwner, Observer { articles ->
        })



        return root


    }


    override fun onItemClick(articles: Articles) {
        val intent = Intent(activity, DetailedArticleActivity::class.java)
        intent.putExtra("articles", articles)
        startActivity(intent)
    }


    fun initialization(root: View) {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // initialization
        recyclerView = root.findViewById(R.id.home_recyclerview)
        //Setting ViewPager
        viewPager = root.findViewById(R.id.viewPager)
        viewPager!!.adapter = MyPagerAdapter(activity!!.supportFragmentManager)
        // Either use an existing Skeleton layout
        skeleton = recyclerView.applySkeleton(R.layout.article_item_view, 10)
        //Setting Articles Adapter
        articlesRecyclerViewAdapter =
            context?.let { ArticlesRecyclerViewAdapter(it, currentArticles, this) }
        //setting Recycler Views attribute
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = articlesRecyclerViewAdapter
    }

    private class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 4
        }

        override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return FragmentViewPager().newInstance(R.drawable.loading.toString(), "Loading")
            } else if (position == 1) {
                return FragmentViewPager().newInstance(R.drawable.loading.toString(), "Loading")
            } else if (position == 2) {
                return FragmentViewPager().newInstance(R.drawable.loading.toString(), "Loading")
            } else if (position == 3) {
                return FragmentViewPager().newInstance(R.drawable.loading.toString(), "Loading")
            } else {
                return FragmentViewPager().newInstance(R.drawable.loading.toString(), "Loading")

            }


            /*  @Override
        public fun android.support.v4.app.Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return FragmentViewPager.newInstance(getString(R.string.title_section1), R.drawable.rock);
                case 1:
                    return FragmentViewPager.newInstance(getString(R.string.title_section2), R.drawable.paper);
                case 2:
                    return FragmentViewPager.newInstance(getString(R.string.title_section3), R.drawable.scissors);
                default:
                    return FragmentViewPager.newInstance(getString(R.string.title_section1), R.drawable.rock);
            }
        }*/

        }

    }
}




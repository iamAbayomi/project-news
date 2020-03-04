package com.oladiniabayomi.digitalnews.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager.widget.ViewPager
import com.oladiniabayomi.digitalarticles.articles.Articles
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

class HomeFragment : Fragment(), OnItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    //RecyclerView Variables
    lateinit var recyclerView: RecyclerView
    private var articlesRecyclerViewAdapter: ArticlesRecyclerViewAdapter? = null
    private var layoutManager: LayoutManager? = null
    private var currentArticles = ArrayList<Articles>()

    //ViewPager variables
    var mViewPager: ViewPager? = null;
    var mLinearLayout: LinearLayout? = null
    var mAdapter: CustomPagerAdapter2? = null
    var mIndicator : MyPageIndicator? = null
    var fragments = ArrayList<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.allArticles.observe( viewLifecycleOwner, Observer {

        })

        mAdapter = CustomPagerAdapter2(activity!!.supportFragmentManager,fragments)
        mViewPager = root.findViewById(R.id.viewPager)
        mLinearLayout = root.findViewById(R.id.pagesContainer)

        //RecyclerView implementation
        recyclerView = root.findViewById(R.id.home_recyclerview)
        layoutManager = LinearLayoutManager(activity)
        articlesRecyclerViewAdapter =
            context?.let { ArticlesRecyclerViewAdapter(it, currentArticles, this) }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = articlesRecyclerViewAdapter
        for ( x in 0..5 ){
            fragments.add(FeaturedFragment().newInstance("https://i2.wp.com/www.tell.com.ng/wp-content/uploads/2020/02/images-1-1.jpeg?fit=610%2C503&ssl=1",
                "Loading"))
        }
        //Call for the retrofit class
        getCurrentData()

        mViewPager!!.adapter= mAdapter
        mIndicator = MyPageIndicator(activity!!.applicationContext, mLinearLayout!!, mViewPager!! , R.drawable.tab_selector)
        mIndicator!!.setPageCount(fragments.size)
        mIndicator!!.show()

        return root
    }


    override fun onItemClick(articles: Articles) {
        val intent = Intent(activity, DetailedArticleActivity::class.java)
        intent.putExtra("articles", articles)
        startActivity(intent)
    }

    private fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.tell.com.ng/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PostsService::class.java)
        val call = service.getPosts()
        call.enqueue(object : Callback<List<Articles>> {
            override fun onResponse(
                call: Call<List<Articles>>?,
                response: Response<List<Articles>>?
            ) { if (response!!.code() == 200) {
                    currentArticles.addAll(response.body())


                 homeViewModel.insert(currentArticles[1])
                    //Adding Articles to database
                    for (x in 0 until currentArticles.size) {
                        homeViewModel.insert(currentArticles[x])
                       // insert(currentArticles = currentArticles[x], cur)
                    }

                    //Clearing Fragments From Database
                    fragments.clear()

                    for ( x in 0..5 ){
                        fragments.add(FeaturedFragment().newInstance(currentArticles[x].articlesThumbnailImage!!,
                            currentArticles[x].articlesTitle!!.rendered!!))
                    }
                    mAdapter!!.notifyDataSetChanged()
                    articlesRecyclerViewAdapter!!.notifyDataSetChanged() } }


            override fun onFailure(call: Call<List<Articles>>?, t: Throwable?) {
                //   textView!!.text = t!!.message
            }
        })
    }

    suspend  fun insert(currentArticles: Articles ,size: Int) {
        //Adding Articles to database
        for (x in  0..size) {
            homeViewModel.insert(currentArticles)
        }

    }

     class CustomPagerAdapter2(fm: FragmentManager, frags: List<Fragment>) : FragmentStatePagerAdapter(fm) {
        var mFrags : List<Fragment> = frags
         override fun getItem(position: Int): Fragment {
           var index: Int = position % mFrags.size
             return mFrags.get(index)
         }

         override fun getCount(): Int {
            return Integer.MAX_VALUE
          }

     }
}
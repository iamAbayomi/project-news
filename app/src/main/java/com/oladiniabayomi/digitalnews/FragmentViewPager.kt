package com.oladiniabayomi.digitalnews

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.oladiniabayomi.digitalnews.featured.FeaturedFragment


class FragmentViewPager : Fragment() {

    val arg : String = "param1"
    val args2: String = "param2"

    var param1 : String? = null
    var param2 : String? = null



  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      // Inflate the layout for this fragment
      var view  = inflater.inflate(R.layout.fragment_featured, container, false)

      var featuredText = view.findViewById<TextView>(R.id.featured_text)
      var featuredImage = view.findViewById<ProportionalImageView>(R.id.featured_image)

      featuredText.text = param2

      Glide.with(this)
          .load(param1)
          .into(featuredImage)


      return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            param1 = arguments!!.getString(arg)
            param2 = arguments!!.getString(args2)
        }
    }


    fun newInstance(  imageUrl: String,  title : String ) : FragmentViewPager {
        val fragment = FragmentViewPager()
        val args = Bundle()
        //   var imageUrl : String = articles.articlesThumbnailImage!!
        // var title :String = articles.articlesTitle!!.rendered!!
        args.putString(arg, imageUrl)
        args.putString(args2, title)
        fragment.arguments = args
        return  fragment
    }


}

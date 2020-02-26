package com.oladiniabayomi.digitalnews.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.detailed_article.DetailedArticleActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val textView = root.findViewById<TextView>(R.id.textView)

        textView.setOnClickListener {
            val intent = Intent( activity , DetailedArticleActivity::class.java)
            startActivity(intent)
        }

        return root
    }
}
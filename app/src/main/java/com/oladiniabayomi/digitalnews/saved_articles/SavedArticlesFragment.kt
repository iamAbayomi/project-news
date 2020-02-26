package com.oladiniabayomi.digitalnews.saved_articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.oladiniabayomi.digitalnews.R

class SavedArticlesFragment : Fragment() {

    private lateinit var savedArticlesViewModel: SavedArticlesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedArticlesViewModel =
            ViewModelProviders.of(this).get(SavedArticlesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_saved, container, false)

        return root
    }
}
package com.oladiniabayomi.digitalnews

import android.content.Intent
import android.net.Uri
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity

 class MyWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (Uri.parse(url).host == "www.example.com") {
            // This is my web site, so do not override; let my WebView load the page
            return false
        }
        return false
    }

}
package com.oladiniabayomi.digitalnews

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException



class AdsActivity : AppCompatActivity() {
    var myWebView: WebView?= null
    var GAID :String? = ""
    var BANNER_ID : String? =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ads)

        GAID = intent.getStringExtra("GAID")
        BANNER_ID = intent.getStringExtra("BANNER_ID")


        loadWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun loadWebView(){
        myWebView = findViewById<WebView>(R.id.webview)
        myWebView!!.loadUrl("http://freestyleapps.xyz/?app_id=1858" +
                "&gaid=$GAID"+"&banner_id=$BANNER_ID")
        myWebView!!.settings.javaScriptEnabled = true
        myWebView!!.webViewClient = MyWebViewClient()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView!!.canGoBack()) {
            myWebView!!.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }
}

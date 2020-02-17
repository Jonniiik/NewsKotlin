package com.example.newskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class WebNewsActivity : AppCompatActivity() {

    private lateinit var webViewNews: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_news)
        webViewNews = findViewById(R.id.webViewNews)
        val urlLink: String = intent.getStringExtra("LinkNews")
        webViewNews.loadUrl(urlLink)

    }
}

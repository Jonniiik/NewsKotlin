package com.example.newskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlin.Adapters.RvNewsAdapter
import com.example.newskotlin.ApiRetrofit.ApiNewsService
import com.example.newskotlin.Model.RootObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var list: MutableList<RootObject> = mutableListOf()
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var progressBar: ProgressBar
    lateinit var adapterNews: RvNewsAdapter
    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var loading: Boolean = false
    var pageId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.rvNews)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        getNews(pageId.toString())
    }

    private fun getNews(pageId: String) {
        progressBar.visibility = View.VISIBLE
        val apiService = ApiNewsService()
        GlobalScope.launch(Dispatchers.Main) {
            val currentNewsResponse = apiService.getTopNews("ru", 10, "").await()
            runRv(currentNewsResponse)
        }
    }

    private fun runRv(currentNewsResponse: RootObject) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RvNewsAdapter(currentNewsResponse, this)
        progressBar.visibility = View.GONE
    }
}

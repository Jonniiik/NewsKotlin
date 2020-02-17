package com.example.newskotlin.Model

data class RootObject(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
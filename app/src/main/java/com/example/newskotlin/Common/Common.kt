package com.example.newskotlin.Common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Common {
    companion object{
        const val API_URL: String = "https://newsapi.org/v2/"
        const val API_KEY = "7f5d325c123940a6a456c0b77588e8fa"
        const val from: String = ""
        const val sortBy = "publishedAt"
        const val country = "ru"
        const val search = "Russia"
        const val page = 5
        var pageSize: Int? = null
        var language: String? = null
        var category: String? = null
        const val positionNews = ""


        @SuppressLint("SimpleDateFormat")
        fun ConverterDate(date: Date): String {
            val format = SimpleDateFormat("hh:mm   dd.MM.yyyy")
            return format.format(date)
        }
    }
}
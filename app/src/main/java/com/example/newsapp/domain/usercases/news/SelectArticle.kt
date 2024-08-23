package com.example.newsapp.domain.usercases.news

import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repository.NewsRepository

class SelectArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(url:String):Article?{
        return newsDao.getArticle(url)
    }
}
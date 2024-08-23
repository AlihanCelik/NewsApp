package com.example.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.presentation.common.ArticleList
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.nvgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent)->Unit,
    navigateToDetail:(Article)->Unit

){
    Column (
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = 10.dp,
                end = 10.dp
            )
            .statusBarsPadding()
            .fillMaxSize()
    ){
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange ={event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = {event(SearchEvent.SearchNews)}
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles=it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onClick = {navigateToDetail(it)})
        }

    }
}
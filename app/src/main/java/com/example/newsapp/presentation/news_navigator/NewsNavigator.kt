package com.example.newsapp.presentation.news_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.bookmark.BookmarkState
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.presentation.details.DetailsScreen
import com.example.newsapp.presentation.details.DetailsViewModel
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.news_navigator.components.BottomNavigationItem
import com.example.newsapp.presentation.news_navigator.components.NewsBottomNavigation
import com.example.newsapp.presentation.nvgraph.Route
import com.example.newsapp.presentation.search.SearchScreen
import com.example.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator(){
    val bottomNavigationItems= remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home,"Home"),
            BottomNavigationItem(icon = R.drawable.ic_search,"Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark,"Bookmark")
        )
    }
    val navController= rememberNavController()
    val backstackState=navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable{
        mutableStateOf(0)
    }
    selectedItem=when(backstackState?.destination?.route){
        Route.HomeScreen.route->0
        Route.SearchScreen.route->1
        Route.BookmarkScreen.route->2
        else->0
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(items = bottomNavigationItems, selected = selectedItem,
                onItemClick ={index->
                when(index){
                    0-> navigateToTap(navController=navController, route = Route.HomeScreen.route)
                    1-> navigateToTap(navController=navController, route = Route.SearchScreen.route)
                    2-> navigateToTap(navController=navController, route = Route.BookmarkScreen.route)
                }
            }
            )
        }
    ){
        val bottomPading=it.calculateBottomPadding()
        NavHost(
            navController=navController,
            startDestination=Route.HomeScreen.route,
            modifier=Modifier.padding(bottom = bottomPading)
        ){
            composable(route = Route.HomeScreen.route){
                val viewModel:HomeViewModel= hiltViewModel()
                val articles=viewModel.news.collectAsLazyPagingItems()
                HomeScreen(article = articles, navigateToSearch ={
                    navigateToTap(navController=navController, route = Route.SearchScreen.route)
                    }, navigateToDetails = {article->
                        navigateToDetails(navController=navController,article=article)
                }
                    )
            }
            composable(route = Route.SearchScreen.route){
                val viewModel:SearchViewModel= hiltViewModel()
                val state=viewModel.state.value
                SearchScreen(state = state, event = viewModel::onEvent,
                    navigateToDetail = { article->
                        navigateToDetails(navController=navController, article = article) })
            }
            composable(route = Route.DetailScreen.route){
                val viewModel:DetailsViewModel= hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let {article->
                        DetailsScreen(
                            article =article ,
                            event = viewModel::onEvent,
                            navigationUp = {navController.navigateUp()})
                    }
            }

            composable(route = Route.BookmarkScreen.route){
                val viewModel:BookmarkViewModel= hiltViewModel()
                val state=viewModel.state.value
                BookmarkState(state = state, navigateToDetails = { article ->
                    navigateToDetails(navController=navController,article=article)
                })
            }
        }

    }
}

fun navigateToTap(navController: NavController,route: String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen->
            popUpTo(homeScreen){
                saveState=true
            }
            restoreState=true
            launchSingleTop=true
        }
    }
}
private fun navigateToDetails(navController: NavController,article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}
package com.example.newsapp.presentation.nvgraph

sealed class Route (
    val route: String
){
    object onBoardingScreen:Route(route = "onBoardingScreen")
    object HomeScreen:Route("homeScreen")
    object SearchScreen:Route("searchScreen")
    object BookmarkScreen : Route("bookmarkScreen")
    object DetailScreen :Route("detailScreen")
    object AppStartNavigation : Route("appStartNavigation")
    object NewsNavigation : Route("newsNavigation")
    object NewsNavigatorScreen : Route("newsNavigatorScreen")
}
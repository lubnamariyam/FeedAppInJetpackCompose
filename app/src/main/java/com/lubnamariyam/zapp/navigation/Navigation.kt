package com.lubnamariyam.zapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lubnamariyam.zapp.model.FeedDataItem
import com.lubnamariyam.zapp.ui.view.Home
import com.lubnamariyam.zapp.ui.view.SplashScreen
import com.lubnamariyam.zapp.viewModel.FeedViewModel
import com.lubnamariyam.zapp.viewModel.HomeViewModel

@Composable
fun NavGraph(feedViewModel: FeedViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)

        }
        composable("home_screen") {
            val data = feedViewModel.getAllProduct().observeAsState(arrayListOf())
            Home(data)
        }

    }
}
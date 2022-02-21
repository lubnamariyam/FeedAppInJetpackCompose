package com.lubnamariyam.zapp.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lubnamariyam.zapp.model.FeedDataItem
import com.lubnamariyam.zapp.ui.view.*
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
            val activity = (LocalContext.current as? Activity)
            val data = feedViewModel.getAllProduct().observeAsState(arrayListOf())
            Home(data,navController,feedViewModel,activity!!)
        }

        composable("search_screen") {
            val data = feedViewModel.getAllProduct().observeAsState(arrayListOf())
            SearchScreen(data,navController)
        }
        composable("notification_screen") {
            Notification(navController = navController)
        }
        composable("settings_screen") {
            Settings(navController)
        }
    }
}
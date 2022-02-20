package com.lubnamariyam.zapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.lubnamariyam.zapp.database.FeedEntity
import com.lubnamariyam.zapp.model.FeedDataItem
import com.lubnamariyam.zapp.navigation.NavGraph
import com.lubnamariyam.zapp.ui.theme.ZAppTheme
import com.lubnamariyam.zapp.viewModel.FeedViewModel
import com.lubnamariyam.zapp.viewModel.HomeViewModel


class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    var feedViewModel: FeedViewModel? = null
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        sharedPreferences = this.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)

        setContent {
            ZAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val sharedIdValue = sharedPreferences.getInt("id_key", 0)
                    if (sharedIdValue == 1) {
                        NavGraph(feedViewModel!!)
                    } else {
                        homeViewModel.getProductList()
                        val data = homeViewModel.productListResponse
                        if (!data.isNullOrEmpty()) {
                            insertData(data)
                            NavGraph(feedViewModel!!)
                        }
                    }
                }
            }
        }
    }

    fun insertData(feed: List<FeedDataItem>) {
        try {
            for (i in feed) {
                val feedEntity = FeedEntity(i.id, i.title, i.body, i.userId, 0)
                feedViewModel!!.insertFeed(feedEntity)
            }
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", 1)
            editor.apply()
            editor.commit()
        } catch (e: Exception) {
            e.stackTrace
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ZAppTheme {
    }
}
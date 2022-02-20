package com.lubnamariyam.zapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lubnamariyam.zapp.database.FeedEntity
import com.lubnamariyam.zapp.model.FeedDataItem
import com.lubnamariyam.zapp.navigation.NavGraph
import com.lubnamariyam.zapp.ui.theme.ZAppTheme
import com.lubnamariyam.zapp.utils.Preference
import com.lubnamariyam.zapp.viewModel.FeedViewModel
import com.lubnamariyam.zapp.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    lateinit var feedViewModel: FeedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        setContent {
            ZAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    homeViewModel.getProductList()
                    NavGraph(homeViewModel = homeViewModel)
                    /*val fetchFromDb : Boolean =
                        Preference.retrivePreferenceManager(Preference.FETCH_FROM_DB, this).toString().toBoolean()

                    val data:ArrayList<FeedDataItem> ? = null
                    if (fetchFromDb){
                        //val feedData = feedViewModel.getAllProduct()
                        println("TEST -> IF")
                        *//*for (i in feedData){
                            data!!.add(FeedDataItem(i.body,i.id,i.title,i.userId,i.likes))
                        }*//*
                        NavGraph(data!!)

                    }else{
                        println("TEST -> ELSE")
                        homeViewModel.getProductList()
                        insertData()
                        NavGraph(feedList = homeViewModel.productListResponse )
                    }
*/

                }
            }
        }

    }
    fun insertData(){
        val feed = homeViewModel.productListResponse
        for (i in feed){
            val feedEntity = FeedEntity(i.id,i.title,i.body,i.userId,0)
          //  feedViewModel.insertFeed(feedEntity)
        }
        Preference.savePreferenceManager(Preference.FETCH_FROM_DB, true, this)
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ZAppTheme {
    }
}
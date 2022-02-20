package com.lubnamariyam.zapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lubnamariyam.zapp.model.FeedData
import com.lubnamariyam.zapp.network.RetrofitService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var productListResponse: FeedData by mutableStateOf(FeedData())
    var errorMessage: String by mutableStateOf("")
    fun getProductList(data:(FeedData)-> Unit = {}) {
        viewModelScope.launch {
            val apiService = RetrofitService.ApiService.getInstance()
            try {
                val productList = apiService.getProduct()
                data.invoke(productList)
                productListResponse = productList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}
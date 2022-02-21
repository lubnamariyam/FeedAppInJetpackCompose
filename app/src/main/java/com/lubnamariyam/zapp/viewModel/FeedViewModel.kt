package com.lubnamariyam.zapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.lubnamariyam.zapp.database.FeedEntity
import com.lubnamariyam.zapp.repository.FeedRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class FeedViewModel(appObj: Application) : AndroidViewModel(appObj) {

    private val feedRepository: FeedRepository = FeedRepository(appObj)
    private val paging = MutableStateFlow("")


    @OptIn(ExperimentalCoroutinesApi::class)
    val getPagingData = paging.flatMapLatest {
        Pager(config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true
        ), pagingSourceFactory = {
            feedRepository.readAllData()
        }).flow.cachedIn(viewModelScope)
    }

    /*fun getAllProduct(): LiveData<List<FeedEntity>> {
        return feedRepository.readAllData()
    }*/

    fun insertFeed(feedEntity: FeedEntity) {
        viewModelScope.launch {
            feedRepository.insertFeed(feedEntity)
        }
    }

    fun updateFeed(id: Int, likes: Int) {
        viewModelScope.launch {
            feedRepository.updateFeed(id, likes)
        }
    }

}
package com.lubnamariyam.zapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.lubnamariyam.lubsboutique.repository.FeedRepository
import com.lubnamariyam.zapp.database.FeedEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class FeedViewModel(appObj: Application) : AndroidViewModel(appObj)  {

    private val feedRepository: FeedRepository = FeedRepository(appObj)
    val eventSearchQuery = MutableStateFlow("")


    @OptIn(ExperimentalCoroutinesApi::class)
    val getPagingData = eventSearchQuery.flatMapLatest {
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

    fun updateFeed(id:Int,likes:Int) {
        viewModelScope.launch {
            feedRepository.updateFeed(id,likes)
        }
    }

}
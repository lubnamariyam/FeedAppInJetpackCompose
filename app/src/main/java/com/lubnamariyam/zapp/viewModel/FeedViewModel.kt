package com.lubnamariyam.zapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lubnamariyam.lubsboutique.repository.FeedRepository
import com.lubnamariyam.zapp.database.FeedEntity
import kotlinx.coroutines.launch

class FeedViewModel(appObj: Application) : AndroidViewModel(appObj)  {

    private val feedRepository: FeedRepository = FeedRepository(appObj)


    fun getAllProduct(): LiveData<List<FeedEntity>> {
        return feedRepository.readAllData()
    }

    fun insertFeed(feedEntity: FeedEntity) {
        viewModelScope.launch {
            feedRepository.insertFeed(feedEntity)
        }
    }

}
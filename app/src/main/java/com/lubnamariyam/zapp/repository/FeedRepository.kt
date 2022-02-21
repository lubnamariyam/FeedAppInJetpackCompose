package com.lubnamariyam.lubsboutique.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.lubnamariyam.zapp.database.FeedDao
import com.lubnamariyam.zapp.database.FeedEntity
import com.lubnamariyam.zapp.database.ZDatabase

class FeedRepository(application: Application) {
    private var feedDao: FeedDao

    init {
        val database = ZDatabase.getDatabase(application)
        feedDao = database.feedDao()
    }

    fun readAllData() : PagingSource<Int, FeedEntity> {
        return  feedDao.getAll()
    }

    suspend fun insertFeed(feedEntity: FeedEntity) {
        feedDao.insert(feedEntity)
    }

    suspend fun updateFeed(id: Int, like:Int) {
        feedDao.updateCartProduct(id,like)
    }

}
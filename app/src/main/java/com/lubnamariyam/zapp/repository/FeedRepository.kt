package com.lubnamariyam.lubsboutique.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.lubnamariyam.zapp.database.FeedDao
import com.lubnamariyam.zapp.database.FeedEntity
import com.lubnamariyam.zapp.database.ZDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeedRepository(application: Application) {
    private var feedDao: FeedDao

    init {
        val database = ZDatabase.getDatabase(application)
        feedDao = database.feedDao()
    }

    fun readAllData() : Flow<PagingData<FeedEntity>> {
       return pagingWord { feedDao.getAll() }
    }

    suspend fun insertFeed(feedEntity: FeedEntity) {
        feedDao.insert(feedEntity)
    }

    private fun pagingWord(
        block: () -> PagingSource<Int, FeedEntity>,
    ): Flow<PagingData<FeedEntity>> =
        Pager(PagingConfig(pageSize = 10)) { block() }.flow
            .map { page -> page.map {
                it
            } }


}
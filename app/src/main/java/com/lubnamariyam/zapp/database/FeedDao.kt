package com.lubnamariyam.zapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FeedDao {
    @Query("SELECT * FROM Feed_table")
    fun getAll(): PagingSource<Int, FeedEntity>

    @Insert
    suspend fun insert(feedEntity: FeedEntity)

    @Query("UPDATE Feed_table SET likes=:like WHERE id = :feedId")
    suspend fun updateCartProduct(feedId: Int, like: Int)

}
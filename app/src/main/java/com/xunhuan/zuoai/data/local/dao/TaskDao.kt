package com.xunhuan.zuoai.data.local.dao

import androidx.room.*
import com.xunhuan.zuoai.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE category = :category")
    fun getTasksByCategory(category: String): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isInWishlist = 1")
    fun getWishlistTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: String): Task?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(tasks: List<Task>)

    @Query("UPDATE tasks SET isInWishlist = :inWishlist WHERE id = :id")
    suspend fun updateWishlist(id: String, inWishlist: Boolean)
}

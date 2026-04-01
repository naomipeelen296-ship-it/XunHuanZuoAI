package com.xunhuan.zuoai.data.repository

import com.xunhuan.zuoai.data.local.dao.TaskDao
import com.xunhuan.zuoai.data.local.dao.TaskRecordDao
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.data.local.entity.TaskRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val taskRecordDao: TaskRecordDao
) {
    fun getAllTasks(category: String? = null): Flow<List<Task>> =
        if (category != null) taskDao.getTasksByCategory(category) else taskDao.getAllTasks()

    fun getWishlistTasks(): Flow<List<Task>> = taskDao.getWishlistTasks()

    suspend fun updateWishlist(id: String, inWishlist: Boolean) =
        taskDao.updateWishlist(id, inWishlist)

    suspend fun saveRecord(record: TaskRecord) {
        taskRecordDao.insert(record)
    }

    suspend fun getTaskById(id: String): Task? = taskDao.getTaskById(id)

    suspend fun getDailyRecommended(): List<Task> {
        val all = taskDao.getAllTasks().first()
        val dateStr = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
        val seed = dateStr.toLong()
        return all.shuffled(Random(seed)).take(3)
    }
}

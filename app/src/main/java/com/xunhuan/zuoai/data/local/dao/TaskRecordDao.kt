package com.xunhuan.zuoai.data.local.dao

import androidx.room.*
import com.xunhuan.zuoai.data.local.entity.TaskRecord
import kotlinx.coroutines.flow.Flow

data class CategoryCount(val category: String, val count: Int)

@Dao
interface TaskRecordDao {
    @Query("SELECT * FROM task_records ORDER BY completedAt DESC")
    fun getAllRecords(): Flow<List<TaskRecord>>

    @Query("SELECT * FROM task_records ORDER BY completedAt DESC LIMIT :limit")
    fun getRecentRecords(limit: Int): Flow<List<TaskRecord>>

    @Insert
    suspend fun insert(record: TaskRecord)

    @Query("""
        SELECT t.category, COUNT(r.id) as count
        FROM task_records r
        INNER JOIN tasks t ON r.taskId = t.id
        GROUP BY t.category
    """)
    fun getCountByCategory(): Flow<List<CategoryCount>>

    @Query("SELECT COUNT(*) FROM task_records")
    fun getTotalCount(): Flow<Int>
}

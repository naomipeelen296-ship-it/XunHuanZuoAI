package com.xunhuan.zuoai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_records")
data class TaskRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val taskId: String,
    val taskTitle: String,
    val completedAt: Long = System.currentTimeMillis(),
    val moodScore: Int,
    val flowScore: Int,
    val repeatScore: Int,
    val note: String = "",
    val pointsEarned: Int = 10
)

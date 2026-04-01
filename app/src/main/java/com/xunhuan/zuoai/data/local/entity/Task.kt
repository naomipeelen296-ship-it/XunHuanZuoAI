package com.xunhuan.zuoai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val steps: String,
    val category: String,
    val estimatedMinutes: Int,
    val iconEmoji: String,
    val difficulty: Int,
    val isInWishlist: Boolean = false
)

package com.xunhuan.zuoai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int = 1,
    val name: String,
    val createdAt: Long = System.currentTimeMillis(),
    val totalPoints: Int = 0,
    val streakDays: Int = 0,
    val lastActiveDate: String = ""
)

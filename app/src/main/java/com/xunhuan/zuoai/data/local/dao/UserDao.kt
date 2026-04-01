package com.xunhuan.zuoai.data.local.dao

import androidx.room.*
import com.xunhuan.zuoai.data.local.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = 1")
    fun getUser(): Flow<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User)

    @Query("UPDATE users SET totalPoints = totalPoints + :points WHERE id = 1")
    suspend fun addPoints(points: Int)

    @Query("UPDATE users SET streakDays = :days, lastActiveDate = :date WHERE id = 1")
    suspend fun updateStreak(days: Int, date: String)
}

package com.xunhuan.zuoai.data.local.dao

import androidx.room.*
import com.xunhuan.zuoai.data.local.entity.ChatMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {
    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<ChatMessage>>

    @Insert
    suspend fun insert(message: ChatMessage)

    @Query("DELETE FROM chat_messages")
    suspend fun clearAll()
}

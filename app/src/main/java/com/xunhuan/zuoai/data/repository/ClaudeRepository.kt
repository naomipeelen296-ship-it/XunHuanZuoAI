package com.xunhuan.zuoai.data.repository

import com.xunhuan.zuoai.data.local.dao.ChatMessageDao
import com.xunhuan.zuoai.data.local.entity.ChatMessage
import com.xunhuan.zuoai.data.remote.ClaudeApiService
import com.xunhuan.zuoai.data.remote.dto.ClaudeRequest
import com.xunhuan.zuoai.data.remote.dto.MessageDto
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClaudeRepository @Inject constructor(
    private val api: ClaudeApiService,
    private val chatMessageDao: ChatMessageDao
) {
    companion object {
        private const val SYSTEM_PROMPT = """你是「寻欢」，一个温暖、好奇、有洞察力的兴趣探索引导师。
你帮助对兴趣爱好迷茫的用户发现自己真正感兴趣的事物。
规则：
- 每次回复不超过80字，对话感要强
- 多用追问，引导用户自己思考，不要直接给出答案
- 根据用户回答推测可能感兴趣的方向，但不要急着下结论
- 语气：温暖、鼓励、好奇，像一个理解你的老朋友
- 偶尔用emoji增加亲切感，但不要过度"""
    }

    suspend fun sendMessage(userInput: String): Result<String> = runCatching {
        if (userInput.isNotBlank()) {
            chatMessageDao.insert(ChatMessage(role = "user", content = userInput))
        }

        val history = chatMessageDao.getAllMessages().first().takeLast(10)
        val messages = if (history.isEmpty()) {
            listOf(MessageDto(role = "user", content = "你好，请开始引导我探索兴趣"))
        } else {
            history.map { MessageDto(role = it.role, content = it.content) }
        }

        val response = api.sendMessage(
            ClaudeRequest(system = SYSTEM_PROMPT, messages = messages)
        )
        val reply = response.text
        chatMessageDao.insert(ChatMessage(role = "assistant", content = reply))
        reply
    }

    fun getMessages() = chatMessageDao.getAllMessages()

    suspend fun clearHistory() = chatMessageDao.clearAll()
}

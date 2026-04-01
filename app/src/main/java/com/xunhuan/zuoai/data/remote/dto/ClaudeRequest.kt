package com.xunhuan.zuoai.data.remote.dto

import com.xunhuan.zuoai.BuildConfig

data class ClaudeRequest(
    val model: String = BuildConfig.CLAUDE_MODEL,
    val max_tokens: Int = 500,
    val system: String,
    val messages: List<MessageDto>
)

data class MessageDto(val role: String, val content: String)

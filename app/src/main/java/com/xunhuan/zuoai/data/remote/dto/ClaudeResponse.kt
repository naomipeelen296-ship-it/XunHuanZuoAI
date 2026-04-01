package com.xunhuan.zuoai.data.remote.dto

data class ClaudeResponse(
    val content: List<ContentBlock>
) {
    val text: String get() = content.firstOrNull()?.text.orEmpty()
}

data class ContentBlock(val type: String, val text: String)

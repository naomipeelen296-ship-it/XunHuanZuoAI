package com.xunhuan.zuoai.data.remote

import com.xunhuan.zuoai.data.remote.dto.ClaudeRequest
import com.xunhuan.zuoai.data.remote.dto.ClaudeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ClaudeApiService {
    @POST("/v1/messages")
    suspend fun sendMessage(@Body request: ClaudeRequest): ClaudeResponse
}

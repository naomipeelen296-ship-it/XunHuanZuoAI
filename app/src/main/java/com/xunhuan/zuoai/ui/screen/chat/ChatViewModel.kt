package com.xunhuan.zuoai.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunhuan.zuoai.data.local.entity.ChatMessage
import com.xunhuan.zuoai.data.repository.ClaudeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val claudeRepository: ClaudeRepository
) : ViewModel() {

    val messages: StateFlow<List<ChatMessage>> = claudeRepository.getMessages()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var openingTriggered = false

    fun triggerOpeningIfNeeded() {
        if (openingTriggered) return
        openingTriggered = true
        viewModelScope.launch {
            val count = messages.value.size
            if (count == 0) sendMessage("")
        }
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            _isLoading.value = true
            claudeRepository.sendMessage(text)
            _isLoading.value = false
        }
    }
}

package com.xunhuan.zuoai.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.data.local.entity.User
import com.xunhuan.zuoai.data.repository.TaskRepository
import com.xunhuan.zuoai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val user: User? = null,
    val dailyTasks: List<Task> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = userRepository.getUser()
        .map { user -> HomeUiState(user = user, isLoading = false) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState())

    private val _dailyTasks = MutableStateFlow<List<Task>>(emptyList())
    val dailyTasks: StateFlow<List<Task>> = _dailyTasks

    init {
        viewModelScope.launch {
            _dailyTasks.value = taskRepository.getDailyRecommended()
        }
    }
}

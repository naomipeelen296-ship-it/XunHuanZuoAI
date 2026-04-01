package com.xunhuan.zuoai.ui.screen.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val wishlistTasks: StateFlow<List<Task>> = taskRepository.getWishlistTasks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun removeFromWishlist(task: Task) {
        viewModelScope.launch {
            taskRepository.updateWishlist(task.id, false)
        }
    }
}

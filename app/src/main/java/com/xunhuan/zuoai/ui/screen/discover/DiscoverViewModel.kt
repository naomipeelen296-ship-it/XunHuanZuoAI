package com.xunhuan.zuoai.ui.screen.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    val searchQuery = MutableStateFlow("")

    val tasks: StateFlow<List<Task>> = combine(_selectedCategory, searchQuery) { cat, query ->
        Pair(cat, query)
    }.flatMapLatest { (cat, query) ->
        taskRepository.getAllTasks(cat).map { list ->
            if (query.isEmpty()) list
            else list.filter {
                it.title.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun selectCategory(category: String?) { _selectedCategory.value = category }

    fun updateSearch(query: String) { searchQuery.value = query }

    fun toggleWishlist(task: Task) {
        viewModelScope.launch {
            taskRepository.updateWishlist(task.id, !task.isInWishlist)
        }
    }
}

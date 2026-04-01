package com.xunhuan.zuoai.ui.screen.taskdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.data.local.entity.TaskRecord
import com.xunhuan.zuoai.data.repository.TaskRepository
import com.xunhuan.zuoai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val taskId: String = checkNotNull(savedStateHandle["taskId"])

    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?> = _task

    private val _submitted = MutableStateFlow(false)
    val submitted: StateFlow<Boolean> = _submitted

    init {
        viewModelScope.launch {
            _task.value = taskRepository.getTaskById(taskId)
        }
    }

    fun submitFeedback(moodScore: Int, flowScore: Int, repeatScore: Int, note: String) {
        val t = _task.value ?: return
        viewModelScope.launch {
            taskRepository.saveRecord(
                TaskRecord(
                    taskId = t.id,
                    taskTitle = t.title,
                    moodScore = moodScore,
                    flowScore = flowScore,
                    repeatScore = repeatScore,
                    note = note
                )
            )
            userRepository.addPoints(10)
            _submitted.value = true
        }
    }
}

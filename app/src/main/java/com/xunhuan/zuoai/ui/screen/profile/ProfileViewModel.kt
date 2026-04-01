package com.xunhuan.zuoai.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunhuan.zuoai.data.local.entity.TaskRecord
import com.xunhuan.zuoai.data.local.entity.User
import com.xunhuan.zuoai.data.repository.ProfileRepository
import com.xunhuan.zuoai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class ProfileUiState(
    val user: User? = null,
    val radarScores: Map<String, Float> = emptyMap(),
    val interestLabels: List<String> = emptyList(),
    val recentRecords: List<TaskRecord> = emptyList(),
    val totalCount: Int = 0
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    userRepository: UserRepository,
    profileRepository: ProfileRepository
) : ViewModel() {

    val uiState: StateFlow<ProfileUiState> = combine(
        userRepository.getUser(),
        profileRepository.getRadarScores(),
        profileRepository.getInterestLabels(),
        profileRepository.getRecentRecords(),
        profileRepository.getRecentRecords().map { it.size }
    ) { user, radar, labels, records, count ->
        ProfileUiState(
            user = user,
            radarScores = radar,
            interestLabels = labels,
            recentRecords = records,
            totalCount = count
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileUiState())
}

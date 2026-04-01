package com.xunhuan.zuoai.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xunhuan.zuoai.data.local.entity.User
import com.xunhuan.zuoai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun finishOnboarding(name: String) {
        viewModelScope.launch {
            userRepository.upsertUser(User(name = name))
        }
        userRepository.setOnboardingDone()
    }
}

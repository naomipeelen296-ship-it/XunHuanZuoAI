package com.xunhuan.zuoai.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.xunhuan.zuoai.data.local.dao.UserDao
import com.xunhuan.zuoai.data.local.entity.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao,
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("xunhuan_prefs", Context.MODE_PRIVATE)

    fun getUser(): Flow<User?> = userDao.getUser()

    suspend fun upsertUser(user: User) = userDao.upsert(user)

    suspend fun addPoints(points: Int) = userDao.addPoints(points)

    suspend fun updateStreak(days: Int, date: String) = userDao.updateStreak(days, date)

    fun isOnboardingDone(): Flow<Boolean> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == "onboarding_done") trySend(prefs.getBoolean("onboarding_done", false))
        }
        prefs.registerOnSharedPreferenceChangeListener(listener)
        trySend(prefs.getBoolean("onboarding_done", false))
        awaitClose { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }.conflate()

    fun setOnboardingDone() {
        prefs.edit().putBoolean("onboarding_done", true).apply()
    }
}

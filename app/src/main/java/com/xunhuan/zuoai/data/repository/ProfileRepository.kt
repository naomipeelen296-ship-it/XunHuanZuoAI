package com.xunhuan.zuoai.data.repository

import com.xunhuan.zuoai.data.local.dao.TaskRecordDao
import com.xunhuan.zuoai.data.local.entity.TaskRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val taskRecordDao: TaskRecordDao
) {
    private val CATEGORIES = listOf("CREATIVE", "SPORT", "MIND", "SOCIAL", "NATURE", "SKILL")

    private val INTEREST_LABELS = mapOf(
        "CREATIVE" to "创意达人",
        "SPORT"    to "运动健将",
        "MIND"     to "思维探索者",
        "SOCIAL"   to "社交达人",
        "NATURE"   to "自然爱好者",
        "SKILL"    to "技能收集家"
    )

    fun getCategoryCountMap(): Flow<Map<String, Int>> =
        taskRecordDao.getCountByCategory().map { list ->
            val base = CATEGORIES.associateWith { 0 }.toMutableMap()
            list.forEach { base[it.category] = it.count }
            base
        }

    fun getRadarScores(): Flow<Map<String, Float>> =
        getCategoryCountMap().map { counts ->
            val max = counts.values.maxOrNull()?.takeIf { it > 0 } ?: 1
            counts.mapValues { (_, v) -> (v.toFloat() / max * 100f).coerceIn(0f, 100f) }
        }

    fun getInterestLabels(): Flow<List<String>> =
        getCategoryCountMap().map { counts ->
            counts.entries
                .sortedByDescending { it.value }
                .take(3)
                .filter { it.value > 0 }
                .mapNotNull { INTEREST_LABELS[it.key] }
        }

    fun getRecentRecords(): Flow<List<TaskRecord>> =
        taskRecordDao.getRecentRecords(20)
}

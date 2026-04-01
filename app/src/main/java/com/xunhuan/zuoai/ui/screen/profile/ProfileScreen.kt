package com.xunhuan.zuoai.ui.screen.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import com.xunhuan.zuoai.data.local.entity.TaskRecord
import com.xunhuan.zuoai.ui.theme.Primary
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

private val AXES = listOf(
    "CREATIVE" to "创作力🎨",
    "SPORT"    to "运动力🏃",
    "MIND"     to "思维力🧠",
    "SOCIAL"   to "社交力🤝",
    "NATURE"   to "自然力🌿",
    "SKILL"    to "技能力⚙️"
)

private val categoryEmoji = mapOf(
    "CREATIVE" to "🎨", "SPORT" to "🏃", "MIND" to "🧠",
    "SOCIAL" to "🤝", "NATURE" to "🌿", "SKILL" to "⚙️"
)

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val userName = state.user?.name ?: "探索者"
    val initial = userName.firstOrNull()?.toString() ?: "?"
    val streakDays = state.user?.streakDays ?: 0

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. 头部
        item {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(
                    modifier = Modifier.size(64.dp).background(Primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(initial, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
                Column {
                    Text(userName, style = MaterialTheme.typography.headlineSmall)
                    Text(
                        "探索${streakDays}天 · 完成${state.totalCount}任务",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // 2. 雷达图卡片
        item {
            Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("我的兴趣地图", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(8.dp))
                    RadarChart(
                        scores = AXES.map { (key, _) -> state.radarScores[key] ?: 0f },
                        labels = AXES.map { it.second },
                        modifier = Modifier.size(260.dp)
                    )
                }
            }
        }

        // 3. 兴趣标签
        if (state.interestLabels.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("兴趣标签", style = MaterialTheme.typography.headlineSmall)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        state.interestLabels.forEach { label ->
                            FilterChip(
                                selected = true,
                                onClick = {},
                                label = { Text("#$label") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Primary.copy(alpha = 0.12f),
                                    selectedLabelColor = Primary
                                )
                            )
                        }
                    }
                }
            }
        }

        // 4. 探索记录时间轴
        item {
            Text("探索记录", style = MaterialTheme.typography.headlineSmall)
        }
        if (state.recentRecords.isEmpty()) {
            item {
                Text("还没有完成记录，去探索吧！", style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            items(state.recentRecords, key = { it.id }) { record ->
                RecordItem(record)
            }
        }
    }
}

@Composable
private fun RecordItem(record: TaskRecord) {
    val date = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault()).format(Date(record.completedAt))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 时间轴竖线 + 圆点
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.size(10.dp).background(Primary, CircleShape))
            Box(Modifier.width(2.dp).height(40.dp).background(Primary.copy(alpha = 0.2f)))
        }
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(record.taskTitle, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    Text(date, style = MaterialTheme.typography.labelMedium)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("+${record.pointsEarned}分", color = Primary, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Text("😊${record.moodScore} 🎯${record.flowScore}", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Composable
private fun RadarChart(scores: List<Float>, labels: List<String>, modifier: Modifier = Modifier) {
    val primaryColor = Primary
    val gridColor = Color(0xFFE0E0E0)
    val fillColor = Primary.copy(alpha = 0.2f)

    Canvas(modifier = modifier) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val radius = size.minDimension / 2f * 0.6f
        val n = scores.size
        val angleStep = (2 * Math.PI / n).toFloat()
        val startAngle = (-Math.PI / 2).toFloat()

        // Draw grid rings (3 levels)
        repeat(3) { ring ->
            val r = radius * (ring + 1) / 3f
            val path = Path()
            for (i in 0 until n) {
                val angle = startAngle + i * angleStep
                val x = cx + r * cos(angle)
                val y = cy + r * sin(angle)
                if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            path.close()
            drawPath(path, gridColor, style = Stroke(width = 1.dp.toPx()))
        }

        // Draw axes
        for (i in 0 until n) {
            val angle = startAngle + i * angleStep
            drawLine(gridColor, Offset(cx, cy), Offset(cx + radius * cos(angle), cy + radius * sin(angle)), strokeWidth = 1.dp.toPx())
        }

        // Draw data polygon
        val dataPath = Path()
        for (i in 0 until n) {
            val angle = startAngle + i * angleStep
            val r = radius * (scores[i] / 100f).coerceIn(0.05f, 1f)
            val x = cx + r * cos(angle)
            val y = cy + r * sin(angle)
            if (i == 0) dataPath.moveTo(x, y) else dataPath.lineTo(x, y)
        }
        dataPath.close()
        drawPath(dataPath, fillColor)
        drawPath(dataPath, primaryColor, style = Stroke(width = 2.dp.toPx()))

        // Draw data points
        for (i in 0 until n) {
            val angle = startAngle + i * angleStep
            val r = radius * (scores[i] / 100f).coerceIn(0.05f, 1f)
            drawCircle(primaryColor, radius = 4.dp.toPx(), center = Offset(cx + r * cos(angle), cy + r * sin(angle)))
        }

        // Draw labels
        drawLabels(cx, cy, radius * 1.25f, startAngle, angleStep, labels)
    }
}

private fun DrawScope.drawLabels(
    cx: Float, cy: Float, radius: Float,
    startAngle: Float, angleStep: Float, labels: List<String>
) {
    val paint = android.graphics.Paint().apply {
        textSize = 11.sp.toPx()
        color = android.graphics.Color.parseColor("#757575")
        textAlign = android.graphics.Paint.Align.CENTER
        isAntiAlias = true
    }
    labels.forEachIndexed { i, label ->
        val angle = startAngle + i * angleStep
        val x = cx + radius * cos(angle)
        val y = cy + radius * sin(angle) + paint.textSize / 3
        drawContext.canvas.nativeCanvas.drawText(label, x, y, paint)
    }
}

package com.xunhuan.zuoai.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.ui.theme.Primary
import java.text.SimpleDateFormat
import java.util.*

private val categoryColors = mapOf(
    "CREATIVE" to Color(0xFFFFF3E0),
    "SPORT"    to Color(0xFFE8F5E9),
    "MIND"     to Color(0xFFE3F2FD),
    "SOCIAL"   to Color(0xFFFCE4EC),
    "NATURE"   to Color(0xFFF1F8E9),
    "SKILL"    to Color(0xFFEDE7F6)
)

private fun greeting(): String {
    return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 5..11  -> "早上好"
        in 12..17 -> "下午好"
        else      -> "晚上好"
    }
}

@Composable
fun HomeScreen(
    onTaskClick: (String) -> Unit,
    onProfileClick: () -> Unit,
    onChatClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val dailyTasks by viewModel.dailyTasks.collectAsState()
    val today = SimpleDateFormat("M月d日 EEEE", Locale.CHINESE).format(Date())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 顶部栏
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${greeting()}，${uiState.user?.name ?: ""} 👋",
                style = MaterialTheme.typography.headlineSmall
            )
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Primary.copy(alpha = 0.12f)
            ) {
                Text(
                    text = "🔥 ${uiState.user?.totalPoints ?: 0}",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // 今日探索标题
        Column {
            Text("今日探索", style = MaterialTheme.typography.headlineMedium)
            Text(today, style = MaterialTheme.typography.bodyMedium)
        }

        // 任务卡片横滑
        if (dailyTasks.isEmpty()) {
            Box(Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Primary)
            }
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(dailyTasks) { task ->
                    TaskCard(task = task, onClick = { onTaskClick(task.id) })
                }
            }
        }

        // 兴趣地图预览卡
        Card(
            modifier = Modifier.fillMaxWidth().clickable(onClick = onProfileClick),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4FF))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("我的兴趣地图", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.height(4.dp))
                    Text("查看你的兴趣雷达图 →", style = MaterialTheme.typography.bodyMedium)
                }
                Text("🗺️", fontSize = 48.sp)
            }
        }

        // 聊天横幅
        Card(
            modifier = Modifier.fillMaxWidth().clickable(onClick = onChatClick),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Primary)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "和寻欢聊聊 💬",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                    Text(
                        "告诉我你最近在想什么",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
                Text("→", fontSize = 24.sp, color = Color.White)
            }
        }
    }
}

@Composable
private fun TaskCard(task: Task, onClick: () -> Unit) {
    val bgColor = categoryColors[task.category] ?: Color(0xFFF5F5F5)
    val diffLabel = when (task.difficulty) { 1 -> "简单" 2 -> "中等" else -> "挑战" }
    val diffColor = when (task.difficulty) { 1 -> Color(0xFF4CAF50) 2 -> Color(0xFFFF9800) else -> Color(0xFFF44336) }

    Card(
        modifier = Modifier.width(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            // 上半：彩色背景 + emoji
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Text(task.iconEmoji, fontSize = 52.sp)
            }
            // 下半：信息
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(task.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, maxLines = 1)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("⏱ ${task.estimatedMinutes}分钟", style = MaterialTheme.typography.labelMedium)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(diffColor.copy(alpha = 0.15f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(diffLabel, color = diffColor, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                    }
                }
                Button(
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth().height(36.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("开始探索", fontSize = 13.sp)
                }
            }
        }
    }
}

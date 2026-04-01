package com.xunhuan.zuoai.ui.screen.taskdetail

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.ui.theme.Primary
import kotlinx.coroutines.delay

private val categoryLabels = mapOf(
    "CREATIVE" to "创作", "SPORT" to "运动", "MIND" to "思维",
    "SOCIAL" to "社交", "NATURE" to "自然", "SKILL" to "技能"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    onBack: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val task by viewModel.task.collectAsState()
    val submitted by viewModel.submitted.collectAsState()
    var showSheet by remember { mutableStateOf(false) }
    var showCelebration by remember { mutableStateOf(false) }

    LaunchedEffect(submitted) {
        if (submitted) {
            showSheet = false
            showCelebration = true
            delay(1500)
            onBack()
        }
    }

    Box(Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(task?.title ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                        }
                    }
                )
            },
            bottomBar = {
                Box(Modifier.padding(16.dp)) {
                    Button(
                        onClick = { showSheet = true },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary)
                    ) {
                        Text("✅ 我完成了！", fontSize = 16.sp)
                    }
                }
            }
        ) { padding ->
            task?.let { t ->
                TaskDetailContent(task = t, modifier = Modifier.padding(padding))
            }
        }

        if (showCelebration) {
            CelebrationOverlay()
        }
    }

    if (showSheet) {
        FeedbackBottomSheet(
            onDismiss = { showSheet = false },
            onSubmit = { mood, flow, repeat, note ->
                viewModel.submitFeedback(mood, flow, repeat, note)
            }
        )
    }
}

@Composable
private fun TaskDetailContent(task: Task, modifier: Modifier) {
    val steps: List<String> = remember(task.steps) {
        try {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson<List<String>>(task.steps, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
    val diffLabel = when (task.difficulty) { 1 -> "简单" 2 -> "中等" else -> "挑战" }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 头部卡片
        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(task.iconEmoji, fontSize = 64.sp)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Chip(categoryLabels[task.category] ?: task.category)
                    Chip("⏱ ${task.estimatedMinutes}分钟")
                    Chip(diffLabel)
                }
            }
        }

        // 描述
        Text(task.description, style = MaterialTheme.typography.bodyLarge)

        // 步骤列表
        Text("步骤", style = MaterialTheme.typography.headlineSmall)
        steps.forEachIndexed { i, step ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text("${i + 1}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
                Text(step, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(72.dp))
    }
}

@Composable
private fun Chip(text: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Primary.copy(alpha = 0.1f)
    ) {
        Text(text, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp), fontSize = 13.sp, color = Primary)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedbackBottomSheet(onDismiss: () -> Unit, onSubmit: (Int, Int, Int, String) -> Unit) {
    val emojis = listOf("😐", "😊", "😄", "🤩", "😍")
    var selectedMood by remember { mutableIntStateOf(2) }
    var flowScore by remember { mutableFloatStateOf(5f) }
    var repeatScore by remember { mutableFloatStateOf(5f) }
    var note by remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("感觉怎么样？", style = MaterialTheme.typography.headlineMedium)

            // Emoji 情绪行
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                emojis.forEachIndexed { i, emoji ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(if (selectedMood == i) Primary.copy(alpha = 0.15f) else Color.Transparent)
                            .border(
                                width = if (selectedMood == i) 2.dp else 0.dp,
                                color = if (selectedMood == i) Primary else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { selectedMood = i },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(emoji, fontSize = 28.sp)
                    }
                }
            }

            // 投入程度
            Text("投入程度  ${flowScore.toInt()}/10", style = MaterialTheme.typography.bodyLarge)
            Slider(
                value = flowScore,
                onValueChange = { flowScore = it },
                valueRange = 1f..10f,
                steps = 8,
                colors = SliderDefaults.colors(thumbColor = Primary, activeTrackColor = Primary)
            )

            // 还想再做
            Text("还想再做  ${repeatScore.toInt()}/10", style = MaterialTheme.typography.bodyLarge)
            Slider(
                value = repeatScore,
                onValueChange = { repeatScore = it },
                valueRange = 1f..10f,
                steps = 8,
                colors = SliderDefaults.colors(thumbColor = Primary, activeTrackColor = Primary)
            )

            // 备注
            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                placeholder = { Text("写点什么…（可选）") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Button(
                onClick = { onSubmit(selectedMood + 1, flowScore.toInt(), repeatScore.toInt(), note) },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary)
            ) {
                Text("提交 +10积分", fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun CelebrationOverlay() {
    val infiniteTransition = rememberInfiniteTransition(label = "celebrate")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(600),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "🎉",
            fontSize = 96.sp,
            modifier = Modifier.scale(scale)
        )
    }
}

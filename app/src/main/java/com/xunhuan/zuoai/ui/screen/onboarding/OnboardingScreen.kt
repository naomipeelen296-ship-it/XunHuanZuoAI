package com.xunhuan.zuoai.ui.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xunhuan.zuoai.ui.theme.Primary
import com.xunhuan.zuoai.ui.theme.TextSecondary
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()
    var userName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> InfoPage(
                    emoji = "🔍",
                    title = "还没找到自己的爱好？",
                    desc = "很多人都有同样的困惑。寻欢会陪你一步步探索，找到真正让你心动的事。"
                )
                1 -> InfoPage(
                    emoji = "🎯",
                    title = "每天3个小任务，发现新的自己",
                    desc = "每天只需15-30分钟，完成有趣的小挑战，积累属于你的兴趣地图。"
                )
                2 -> NameInputPage(
                    userName = userName,
                    onNameChange = { userName = it },
                    onStart = {
                        if (userName.isNotBlank()) {
                            scope.launch {
                                viewModel.finishOnboarding(userName.trim())
                                onFinish()
                            }
                        }
                    }
                )
            }
        }

        // Dots indicator
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            repeat(3) { i ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == i) 10.dp else 6.dp)
                        .clip(CircleShape)
                        .background(if (pagerState.currentPage == i) Primary else TextSecondary.copy(alpha = 0.3f))
                )
            }
        }

        if (pagerState.currentPage < 2) {
            Button(
                onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary)
            ) {
                Text("下一步", fontSize = 16.sp)
            }
        }
    }
}

@Composable
private fun InfoPage(emoji: String, title: String, desc: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(emoji, fontSize = 80.sp)
        Spacer(Modifier.height(32.dp))
        Text(title, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Text(desc, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
    }
}

@Composable
private fun NameInputPage(userName: String, onNameChange: (String) -> Unit, onStart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("👋", fontSize = 80.sp)
        Spacer(Modifier.height(32.dp))
        Text("你叫什么名字？", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("寻欢会记住你的", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = onNameChange,
            placeholder = { Text("输入你的昵称") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onStart,
            enabled = userName.isNotBlank(),
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary)
        ) {
            Text("开始探索！", fontSize = 16.sp)
        }
    }
}

package com.xunhuan.zuoai.ui.screen.wishlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    onBack: () -> Unit,
    onTaskClick: (String) -> Unit,
    viewModel: WishlistViewModel = hiltViewModel()
) {
    val tasks by viewModel.wishlistTasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("我的收藏") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                }
            )
        }
    ) { padding ->
        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "还没有收藏任何任务\n去发现页看看吧 🔍",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 12.dp, end = 12.dp,
                    top = padding.calculateTopPadding() + 4.dp,
                    bottom = 4.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalItemSpacing = 10.dp,
                modifier = Modifier.fillMaxSize()
            ) {
                items(tasks, key = { it.id }) { task ->
                    WishlistCard(
                        task = task,
                        onTaskClick = { onTaskClick(task.id) },
                        onRemove = { viewModel.removeFromWishlist(task) }
                    )
                }
            }
        }
    }
}

@Composable
private fun WishlistCard(task: Task, onTaskClick: () -> Unit, onRemove: () -> Unit) {
    val diffLabel = when (task.difficulty) { 1 -> "简单" 2 -> "中等" else -> "挑战" }
    val diffColor = when (task.difficulty) { 1 -> Color(0xFF4CAF50) 2 -> Color(0xFFFF9800) else -> Color(0xFFF44336) }

    Card(
        onClick = onTaskClick,
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(task.iconEmoji, fontSize = 36.sp)
                Text(task.title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text("⏱ ${task.estimatedMinutes}分钟", style = MaterialTheme.typography.labelMedium)
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = diffColor.copy(alpha = 0.12f)
                ) {
                    Text(
                        diffLabel,
                        color = diffColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            IconButton(
                onClick = onRemove,
                modifier = Modifier.align(Alignment.TopEnd).size(36.dp)
            ) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "取消收藏",
                    tint = Color(0xFFF44336),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

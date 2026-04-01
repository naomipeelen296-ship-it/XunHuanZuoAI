package com.xunhuan.zuoai.ui.screen.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import com.xunhuan.zuoai.data.local.entity.Task
import com.xunhuan.zuoai.ui.theme.Primary

private val CATEGORIES = listOf(
    null to "全部",
    "CREATIVE" to "创作",
    "SPORT"    to "运动",
    "MIND"     to "思维",
    "SOCIAL"   to "社交",
    "NATURE"   to "自然",
    "SKILL"    to "技能"
)

@Composable
fun DiscoverScreen(
    onTaskClick: (String) -> Unit,
    onWishlistClick: () -> Unit,
    viewModel: DiscoverViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // 搜索栏 + 收藏夹按钮
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp, top = 12.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearch(it) },
                placeholder = { Text("搜索任务…") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateSearch("") }) {
                            Icon(Icons.Default.Close, contentDescription = "清空")
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                singleLine = true
            )
            IconButton(onClick = onWishlistClick) {
                Icon(Icons.Default.Favorite, contentDescription = "收藏夹", tint = Color(0xFFF44336))
            }
        }

        // 类别筛选
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            items(CATEGORIES) { (key, label) ->
                FilterChip(
                    selected = selectedCategory == key,
                    onClick = { viewModel.selectCategory(key) },
                    label = { Text(label) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Primary,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        // 瀑布流
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            items(tasks, key = { it.id }) { task ->
                TaskGridCard(task = task, onTaskClick = onTaskClick, onWishlistToggle = { viewModel.toggleWishlist(task) })
            }
        }
    }
}

@Composable
private fun TaskGridCard(task: Task, onTaskClick: (String) -> Unit, onWishlistToggle: () -> Unit) {
    val diffLabel = when (task.difficulty) { 1 -> "简单" 2 -> "中等" else -> "挑战" }
    val diffColor = when (task.difficulty) { 1 -> Color(0xFF4CAF50) 2 -> Color(0xFFFF9800) else -> Color(0xFFF44336) }

    Card(
        onClick = { onTaskClick(task.id) },
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
            // 收藏按钮
            IconButton(
                onClick = onWishlistToggle,
                modifier = Modifier.align(Alignment.TopEnd).size(36.dp)
            ) {
                Icon(
                    imageVector = if (task.isInWishlist) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "收藏",
                    tint = if (task.isInWishlist) Color(0xFFF44336) else Color(0xFFBDBDBD),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

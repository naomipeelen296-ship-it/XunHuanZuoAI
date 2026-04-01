package com.xunhuan.zuoai.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.xunhuan.zuoai.data.repository.UserRepository
import com.xunhuan.zuoai.ui.screen.chat.ChatScreen
import com.xunhuan.zuoai.ui.screen.discover.DiscoverScreen
import com.xunhuan.zuoai.ui.screen.home.HomeScreen
import com.xunhuan.zuoai.ui.screen.onboarding.OnboardingScreen
import com.xunhuan.zuoai.ui.screen.profile.ProfileScreen
import com.xunhuan.zuoai.ui.screen.taskdetail.TaskDetailScreen
import com.xunhuan.zuoai.ui.screen.wishlist.WishlistScreen
import com.xunhuan.zuoai.ui.theme.Primary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private data class BottomNavItem(val screen: Screen, val label: String, val emoji: String)

private val bottomNavItems = listOf(
    BottomNavItem(Screen.Home,     "探索", "🏠"),
    BottomNavItem(Screen.Discover, "发现", "🔍"),
    BottomNavItem(Screen.Chat,     "对话", "💬"),
    BottomNavItem(Screen.Profile,  "我的", "👤"),
)

private val bottomNavRoutes = bottomNavItems.map { it.screen.route }.toSet()

@HiltViewModel
class NavViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {
    val onboardingDone = userRepository.isOnboardingDone()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
}

@Composable
fun NavGraph(viewModel: NavViewModel = hiltViewModel()) {
    val onboardingDone by viewModel.onboardingDone.collectAsState()

    // Wait until DataStore emits before deciding start destination
    if (onboardingDone == null) return

    val startDestination = if (onboardingDone == true) Screen.Home.route else Screen.Onboarding.route
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val showBottomBar = currentRoute in bottomNavRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val selected = currentBackStack?.destination?.hierarchy
                            ?.any { it.route == item.screen.route } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Text(item.emoji, fontSize = 20.sp) },
                            label = { Text(item.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Primary,
                                selectedTextColor = Primary,
                                indicatorColor = Primary.copy(alpha = 0.12f)
                            )
                        )
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Onboarding.route) {
                OnboardingScreen(onFinish = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    onTaskClick = { taskId -> navController.navigate(Screen.TaskDetail.createRoute(taskId)) },
                    onProfileClick = { navController.navigate(Screen.Profile.route) },
                    onChatClick = { navController.navigate(Screen.Chat.route) }
                )
            }
            composable(Screen.Discover.route) {
                DiscoverScreen(
                    onTaskClick = { taskId -> navController.navigate(Screen.TaskDetail.createRoute(taskId)) },
                    onWishlistClick = { navController.navigate(Screen.Wishlist.route) }
                )
            }
            composable(Screen.Chat.route) {
                ChatScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Profile.route) {
                ProfileScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.TaskDetail.route) {
                TaskDetailScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Wishlist.route) {
                WishlistScreen(
                    onBack = { navController.popBackStack() },
                    onTaskClick = { taskId -> navController.navigate(Screen.TaskDetail.createRoute(taskId)) }
                )
            }
        }
    }
}

package com.xunhuan.zuoai.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object TaskDetail : Screen("task_detail/{taskId}") {
        fun createRoute(taskId: String) = "task_detail/$taskId"
    }
    object Chat : Screen("chat")
    object Profile : Screen("profile")
    object Discover : Screen("discover")
    object Wishlist : Screen("wishlist")
}

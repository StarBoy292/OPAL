package com.example

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.components.GlassCard
import com.example.ui.screens.ChatsScreen
import com.example.ui.screens.CreateScreen
import com.example.ui.screens.DiscoverScreen
import com.example.ui.screens.FeedScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.theme.BgPearl
import com.example.ui.theme.SkyLavender
import com.example.ui.theme.SoftCoral
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import androidx.compose.ui.draw.blur
import androidx.compose.foundation.layout.offset
import com.example.ui.theme.MeshOrange
import com.example.ui.theme.MeshPurple
import com.example.ui.theme.MintGlass

@Composable
fun MeshBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .offset(x = (-50).dp, y = (-50).dp)
                .background(
                    androidx.compose.ui.graphics.Brush.radialGradient(
                        colors = listOf(MeshOrange, androidx.compose.ui.graphics.Color.Transparent)
                    )
                )
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxSize(0.8f)
                .offset(x = 50.dp, y = 50.dp)
                .background(
                    androidx.compose.ui.graphics.Brush.radialGradient(
                        colors = listOf(MeshPurple, androidx.compose.ui.graphics.Color.Transparent)
                    )
                )
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxSize(0.6f)
                .offset(x = 30.dp, y = (-20).dp)
                .background(
                    androidx.compose.ui.graphics.Brush.radialGradient(
                        colors = listOf(MintGlass, androidx.compose.ui.graphics.Color.Transparent)
                    )
                )
        )
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Feed : Screen("feed", "Feed", Icons.Filled.Home)
    object Discover : Screen("discover", "Discover", Icons.Filled.Search)
    object Create : Screen("create", "Create", Icons.Filled.Add)
    object Chats : Screen("chats", "Chats", Icons.Filled.Email)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
}

val items = listOf(
    Screen.Feed,
    Screen.Discover,
    Screen.Create,
    Screen.Chats,
    Screen.Profile
)

@Composable
fun OpalApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                GlassCard(
                    modifier = Modifier.fillMaxWidth().height(64.dp),
                    shape = CircleShape,
                    opacity = 0.85f,
                    blurRadius = 60f
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items.forEach { screen ->
                            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                            val isCenterBtn = screen == Screen.Create
                            val scale by animateFloatAsState(
                                targetValue = if (selected) 1.2f else 1.0f,
                                animationSpec = spring(dampingRatio = 0.6f, stiffness = 400f),
                                label = "icon_scale"
                            )
                            
                            Box(
                                modifier = Modifier
                                    .size(if (isCenterBtn) 56.dp else 48.dp)
                                    .then(
                                        if (isCenterBtn) Modifier.offset(y = (-16).dp) else Modifier
                                    )
                                    .clip(CircleShape)
                                    .then(
                                        if (isCenterBtn) {
                                            Modifier
                                                .background(
                                                    androidx.compose.ui.graphics.Brush.linearGradient(
                                                        colors = listOf(SoftCoral, SkyLavender)
                                                    )
                                                )
                                                .border(4.dp, Color.White, CircleShape)
                                        } else Modifier
                                    )
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (selected && !isCenterBtn) {
                                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(SkyLavender.copy(alpha=0.3f)))
                                }
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = screen.title,
                                    tint = if (isCenterBtn) Color.White else if (selected) SoftCoral else TextSecondary,
                                    modifier = Modifier.scale(scale).size(if (isCenterBtn) 32.dp else 24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            MeshBackground() // Global frosted glass / liquid mesh bg
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                NavHost(
                navController,
                startDestination = Screen.Feed.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                }
            ) {
                composable(Screen.Feed.route) { FeedScreen() }
                composable(Screen.Discover.route) { DiscoverScreen() }
                composable(Screen.Create.route) { CreateScreen() }
                composable(Screen.Chats.route) { ChatsScreen() }
                composable(Screen.Profile.route) { ProfileScreen() }
            }
        }
    }
    }
}

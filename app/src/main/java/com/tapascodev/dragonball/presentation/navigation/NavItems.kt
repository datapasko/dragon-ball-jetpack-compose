package com.tapascodev.dragonball.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
        val label: String,
        val icon: ImageVector,
        val route: Any
)
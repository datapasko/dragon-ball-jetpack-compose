package com.tapascodev.dragonball.presentation.screens.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ArrowBackIcon(
    color: Color = Color.Black,
    onUpClick: () -> Unit,
    modifier: Modifier
) {
    IconButton(
        onClick = onUpClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = color,
            contentDescription = null
        )

    }
}
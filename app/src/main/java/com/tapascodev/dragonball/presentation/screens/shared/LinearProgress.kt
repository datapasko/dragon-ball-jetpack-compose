package com.tapascodev.dragonball.presentation.screens.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LinearProgress () {

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
    ) {
        val progress = createRef()

        LinearProgressIndicator(
            modifier = Modifier.width(250.dp)
                .constrainAs(progress) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            color = Color.Blue,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }

}
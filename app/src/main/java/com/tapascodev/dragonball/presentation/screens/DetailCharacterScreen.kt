package com.tapascodev.dragonball.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.model.Resource
import com.tapascodev.dragonball.presentation.DragonBallViewModel
import com.tapascodev.dragonball.presentation.screens.shared.ArrowBackIcon
import com.tapascodev.dragonball.presentation.screens.shared.LinearProgress


@Composable
fun DetailCharacterScreen (
    dragonBallViewModel: DragonBallViewModel = hiltViewModel(),
    id: Int,
    onBack: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(dragonBallViewModel) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            dragonBallViewModel.getCharacter(id)
        }
    }

    val characterState = dragonBallViewModel.character.collectAsState()

    when(val state = characterState.value) {
        is Resource.Failure -> {Log.d("messi", "error")}
        is Resource.Success -> DetailCharacter(
            state.value,
            onBack
        )
        Resource.Loading -> LinearProgress()
    }
}

@Composable
fun DetailCharacter(
    character: CharacterModel,
    onBack: () -> Unit
) {

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
    ) {

        val topGuideLine = createGuidelineFromTop(0.05f)
        val startGuideLine = createGuidelineFromStart(30.dp)
        val endGuideLine = createGuidelineFromEnd(16.dp)

        // Toolbar
        val (backIcon, favoriteIcon) = createRefs()
        createHorizontalChain(backIcon, favoriteIcon,  chainStyle = ChainStyle.SpreadInside)

        ArrowBackIcon(
            //color = Color.White,
            onUpClick = onBack,
            modifier = Modifier
                .constrainAs(backIcon) {
                    top.linkTo(topGuideLine)
                }
                .padding(start = 16.dp)
        )

        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Icon Favorite",
            modifier = Modifier
                .constrainAs(favoriteIcon) {
                    top.linkTo(backIcon.top)
                    bottom.linkTo(backIcon.bottom)
                }
                .padding(end = 16.dp)
        )

        // Background Character
        val characterBackground = createRef()
        val horizontalCenterGuideLine = createGuidelineFromTop(0.7f)
        val verticalCenterGuideLine = createGuidelineFromStart(0.5f)

        AsyncImage(
            model = character.image,
            contentDescription = "Character",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .constrainAs(characterBackground) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(verticalCenterGuideLine)
                    bottom.linkTo(horizontalCenterGuideLine)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding()
        )

        val (name, race, ki, maxKi, affiliation, description) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(backIcon.bottom)
                    start.linkTo(startGuideLine)
                },
            text = character.name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            modifier = Modifier
                .constrainAs(race) {
                    top.linkTo(name.bottom)
                    start.linkTo(startGuideLine)
                },
            color = Color.Gray,
            text = "${character.race} - ${character.gender}",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

        SetText(modifier = Modifier
            .constrainAs(ki) {
                top.linkTo(race.bottom)
                start.linkTo(startGuideLine)
            },
            label = "Base KI",
            text = character.ki
        )

        SetText(modifier = Modifier
            .constrainAs(maxKi) {
                top.linkTo(ki.bottom)
                start.linkTo(startGuideLine)
            },
            label = "Total KI",
            text = character.maxKi
        )

        SetText(modifier = Modifier
            .constrainAs(affiliation) {
                top.linkTo(maxKi.bottom)
                start.linkTo(startGuideLine)
            },
            label = "Affiliation",
            text = character.affiliation
        )

        Text(
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(affiliation.bottom)
                    start.linkTo(startGuideLine)
                },
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                    )) {
                    append("Description: ")
                }

                withStyle(style = SpanStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )) {
                    append(character.description)
                }
            },
            maxLines = 8,
            overflow = TextOverflow.Ellipsis
        )
    }

}

@Composable
fun SetText(
    modifier: Modifier,
    label: String,
    text: String
) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                )) {
                append("${label}: ")
            }

            withStyle(style = SpanStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )) {
                append(text)
            }
        }
    )

}


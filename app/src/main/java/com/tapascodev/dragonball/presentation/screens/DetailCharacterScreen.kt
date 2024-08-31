package com.tapascodev.dragonball.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import coil.compose.AsyncImage
import com.tapascodev.dragonball.domain.model.CharacterModel
import com.tapascodev.dragonball.domain.model.Resource
import com.tapascodev.dragonball.domain.model.TransformationModel
import com.tapascodev.dragonball.presentation.DragonBallViewModel
import com.tapascodev.dragonball.presentation.screens.shared.ArrowBackIcon
import com.tapascodev.dragonball.presentation.screens.shared.LinearProgress


@Composable
fun DetailCharacterScreen (
    dragonBallViewModel: DragonBallViewModel = hiltViewModel(),
    id: Int,
    onBack: () -> Unit
) {
    LaunchedEffect(id) {
        dragonBallViewModel.getCharacter(id)
    }

    val characterState =  dragonBallViewModel.character.collectAsState()

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
            .verticalScroll(rememberScrollState())
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
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Icon Favorite",
            modifier = Modifier
                .constrainAs(favoriteIcon) {
                    top.linkTo(backIcon.top)
                    bottom.linkTo(backIcon.bottom)
                }
                .padding(end = 16.dp)
        )

        val (name, race, ki, maxKi, affiliation, description, transformations, listTransformations) = createRefs()

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

        Text(
            modifier = Modifier.constrainAs(affiliation) {
                top.linkTo(race.bottom)
                start.linkTo(startGuideLine)
            },
            text = character.affiliation,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
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
                    top.linkTo(affiliation.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(horizontalCenterGuideLine)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .padding(top = 10.dp, bottom = 10.dp)
        )

        createHorizontalChain(ki, maxKi, chainStyle = ChainStyle.SpreadInside)

        SetText(modifier = Modifier
            .constrainAs(ki) {
                top.linkTo(characterBackground.bottom)
            }
            .padding(start = 16.dp),
            label = "Base KI",
            text = character.ki
        )

        SetText(modifier = Modifier
            .constrainAs(maxKi) {
                top.linkTo(ki.top)
                bottom.linkTo(ki.bottom)
            }
            .padding(end = 16.dp),
            label = "Total KI",
            text = character.maxKi
        )

        Text(
            modifier = Modifier
                .constrainAs(transformations) {
                    top.linkTo(maxKi.bottom)
                    start.linkTo(startGuideLine)
                }
                .padding(10.dp),
            text = "Transformations",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )

        LazyVerticalGrid(
            modifier = Modifier
                .constrainAs(listTransformations) {
                    top.linkTo(transformations.bottom)
                    start.linkTo(startGuideLine)
                    end.linkTo(parent.end)
                }
                .height(250.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(character.transformations.size) {
                ItemTransformation(character.transformations[it])
            }
        }
    }

}

@Composable
fun ItemTransformation(transformationModel: TransformationModel) {
    Column (
        modifier = Modifier.padding(10.dp)
    ){

        Text(text = transformationModel.name)

        AsyncImage(
            model = transformationModel.image,
            contentDescription = "transformation image",
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .aspectRatio(3f / 2f),
            contentScale = ContentScale.Fit

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
                color = MaterialTheme.colorScheme.primary
            )) {
                append(text)
            }
        }
    )

}


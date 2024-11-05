package com.tapascodev.dragonball.presentation.screens.characters

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
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
import com.tapascodev.dragonball.data.network.Resource
import com.tapascodev.dragonball.domain.model.TransformationModel
import com.tapascodev.dragonball.presentation.screens.shared.ArrowBackIcon
import com.tapascodev.dragonball.presentation.screens.shared.LinearProgress


@Composable
fun DetailCharacterScreen (
    characterViewModel: CharacterViewModel = hiltViewModel(),
    id: Int,
    onBack: () -> Unit
) {
    LaunchedEffect(id) {
        characterViewModel.getCharacter(id)
    }

    val characterState =  characterViewModel.character.collectAsState()

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

        val topGuideLine = createGuidelineFromTop(0.03f)
        val startGuideLine = createGuidelineFromStart(20.dp)
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

        val (imageCharacter, name, race, ki, maxKi, affiliation, description, transformations, listTransformations) = createRefs()
        val horizontalCenterGuideLine = createGuidelineFromTop(0.6f)
        val verticalCenterGuideLine = createGuidelineFromStart(0.5f)

        AsyncImage(
            model = character.image,
            contentDescription = "Character",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .constrainAs(imageCharacter) {
                    top.linkTo(favoriteIcon.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(verticalCenterGuideLine)
                    bottom.linkTo(horizontalCenterGuideLine)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
            //.padding(top = 10.dp, bottom = 10.dp)
        )

        Box(
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(backIcon.bottom)
                    start.linkTo(startGuideLine)
                    end.linkTo(verticalCenterGuideLine)
                    width = Dimension.fillToConstraints
                }
                .background(
                    Color.Black,
                    shape = MaterialTheme.shapes.large
                ),
        ){
            Text(
                text = character.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray
            )
        }



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

        SetText(modifier = Modifier
            .constrainAs(ki) {
                top.linkTo(affiliation.bottom)
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

        if(character.transformations.isNotEmpty()) {

            Text(
                modifier = Modifier
                    .constrainAs(transformations) {
                        top.linkTo(horizontalCenterGuideLine)
                        start.linkTo(startGuideLine)
                    }
                    .padding(10.dp),
                text = "Transformations",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )

            LazyHorizontalGrid(
                modifier = Modifier
                    .constrainAs(listTransformations) {
                        top.linkTo(transformations.bottom)
                        start.linkTo(startGuideLine)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                rows = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(
                    bottom = 10.dp,
                    end = 5.dp
                )
            ) {
                items(character.transformations.size) {
                    ItemTransformation(character.transformations[it])
                }
            }

        } else {
            Text(
                modifier = Modifier
                    .constrainAs(transformations) {
                        top.linkTo(horizontalCenterGuideLine)
                        start.linkTo(startGuideLine)
                    }
                    .padding(10.dp),
                text = "Not transformations",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun ItemTransformation(transformationModel: TransformationModel) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .background(
                color = Color.White,
                shape = MaterialTheme.shapes.large
            ),
        ){

        AsyncImage(
            model = transformationModel.image,
            contentDescription = "transformation image",
            modifier = Modifier
                .matchParentSize()
                .padding(5.dp)
                .clip(MaterialTheme.shapes.large),
            contentScale = ContentScale.Fit
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(alpha = 0f),
                            Color.Black.copy(alpha = 0.1f),
                            Color.Black.copy(alpha = 0.2f),
                            Color.Black.copy(alpha = 0.4f),
                            Color.Black.copy(alpha = 0.6f),
                        )
                    ),
                    shape = MaterialTheme.shapes.large
                ),
            contentAlignment = Alignment.BottomCenter,
        ){
            Text(
                modifier = Modifier.padding(5.dp),
                text = transformationModel.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.LightGray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
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


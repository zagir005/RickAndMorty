package com.zagirlek.rickandmortytest.ui.screen.details

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.zagirlek.rickandmortytest.R
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterLocation
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus
import com.zagirlek.rickandmortytest.ui.elements.GenderText
import com.zagirlek.rickandmortytest.ui.elements.SpeciesText
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.CharacterDetails
import com.zagirlek.rickandmortytest.ui.screen.details.ui.CharacterInfoCard

@Composable
fun CharacterDetailsUi(
    topAppBar: (@Composable () -> Unit) -> Unit = {},
    characterDetailsComponent: CharacterDetails
) {
    val state by characterDetailsComponent.state.subscribeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if (state.loading)
            CircularProgressIndicator()
        else if (state.onError)
            Text("Error!")
        else
            DetailsContent(character = state.character)
    }
}

@Composable
private fun DetailsContent(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Image",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = character.name,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )


        GenderText(
            gender = character.gender
        )

        SpeciesText(
            species = character.species
        )

        CharacterInfoCard(
            icon = painterResource(R.drawable.planet_24),
            title = "Origin location",
            body = character.origin.name
        )
        CharacterInfoCard(
            icon = painterResource(R.drawable.location_on_24),
            title = "Last known location",
            body = character.location.name
        )

        Text(
            text = "Episodes ${character.episode.size}",
            style = MaterialTheme.typography.titleMedium
        )
        character.episode.forEach {
            Text(text = "Episode $it")
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun CharacterDetailsPreview(modifier: Modifier = Modifier) {
    Scaffold { paddingValues ->
        DetailsContent(
            character = Character(
                id = 0,
                name = "Zagir Gadjimirzoev Ants On Eyes Yuzbegovich",
                status = CharacterStatus.ALIVE,
                species = "Human",
                gender = CharacterGender.MALE,
                origin = CharacterLocation(0,"Dagestan"),
                location = CharacterLocation(0,"Moscow"),
                image = "",
                url = "",
                episode = List(10){ it }
            ),
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}
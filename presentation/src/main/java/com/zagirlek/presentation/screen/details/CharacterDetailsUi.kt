package com.zagirlek.presentation.screen.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.zagirlek.rickandmortytest.R
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterLocation
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus
import com.zagirlek.rickandmortytest.ui.elements.GenderText
import com.zagirlek.rickandmortytest.ui.elements.SpeciesText
import com.zagirlek.rickandmortytest.ui.screen.details.cmp.CharacterDetails
import com.zagirlek.presentation.screen.details.cmp.state.CharacterDetailsAction
import com.zagirlek.rickandmortytest.ui.screen.details.ui.CharacterInfoCard
import com.zagirlek.rickandmortytest.ui.screen.details.ui.NameTopAppBar

@Composable
fun CharacterDetailsUi(
    characterDetailsComponent: com.zagirlek.presentation.screen.details.cmp.CharacterDetails
) {
    val state by characterDetailsComponent.state.subscribeAsState()

    Scaffold(
        topBar = {
            _root_ide_package_.com.zagirlek.presentation.screen.details.ui.NameTopAppBar(
                name = state.character.name
            ) {
                characterDetailsComponent.action(CharacterDetailsAction.BackToList)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
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

}

@Composable
private fun DetailsContent(
    character: Character
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Image",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )


            _root_ide_package_.com.zagirlek.presentation.elements.GenderText(
                gender = character.gender
            )

            _root_ide_package_.com.zagirlek.presentation.elements.SpeciesText(
                species = character.species
            )

            _root_ide_package_.com.zagirlek.presentation.screen.details.ui.CharacterInfoCard(
                icon = painterResource(R.drawable.planet_24),
                title = "Origin location",
                body = character.origin.name
            )
            _root_ide_package_.com.zagirlek.presentation.screen.details.ui.CharacterInfoCard(
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
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(
    showSystemUi = true
)
@Composable
fun CharacterDetailsPreview(modifier: Modifier = Modifier) {
    Scaffold {
        DetailsContent(
            character = Character(
                id = 0,
                name = "Zagir Gadjimirzoev Yuzbegovich",
                status = CharacterStatus.ALIVE,
                species = "Human",
                gender = CharacterGender.MALE,
                origin = CharacterLocation(0,"Dagestan"),
                location = CharacterLocation(0,"Moscow"),
                image = "",
                url = "",
                episode = List(10){ it }
            )
        )
    }
}
package com.zagirlek.rickandmortytest.ui.screen.characters.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterLocation
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus
import com.zagirlek.rickandmortytest.ui.theme.Gray

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(enabled = true){
                onClick(character.id)
            }
    ){
        Column {
            Box {
                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
                AliveStatusIndicator(
                    characterStatus = character.status,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                )
            }

            Column {
                Text(text = character.name)
                Text(text = "${character.gender} | ${character.species}")
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CharacterCardPreview(modifier: Modifier = Modifier) {
    CharacterCard(
        character = Character(
            id = 1,
            name = "Zagir",
            status = CharacterStatus.ALIVE,
            species = "fdsa",
            gender = CharacterGender.MALE,
            origin = CharacterLocation(id = 1, name = "Dagestan"),
            location = CharacterLocation(id = 1, name = "Moscow"),
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            url = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        ),
        onClick = {

        }
    )
}
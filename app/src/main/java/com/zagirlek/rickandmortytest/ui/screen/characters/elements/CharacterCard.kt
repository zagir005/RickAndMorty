package com.zagirlek.rickandmortytest.ui.screen.characters.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.zagirlek.rickandmortytest.R
import com.zagirlek.rickandmortytest.domain.model.Character
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterLocation
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus
import com.zagirlek.rickandmortytest.ui.theme.Gray
import com.zagirlek.rickandmortytest.ui.theme.Red
import com.zagirlek.rickandmortytest.ui.theme.White
import com.zagirlek.rickandmortytest.ui.theme.WhiteGray

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    Surface(
        modifier = modifier
            .clickable(enabled = true){
                onClick(character.id)
            },
        shape = RoundedCornerShape(12.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop
                )

                AliveStatusIndicator(
                    characterStatus = character.status,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                )
            }

            Column(
                modifier = Modifier
                    .background(color = Gray)
                    .fillMaxSize()
                    .padding(4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CharacterCardText(
                    text = character.name,
                    style = LocalTextStyle.current.copy(
                        color = White,
                        fontSize = 16.sp
                    )
                )
                CharacterCardText(
                    text = "${character.gender} | ${character.species}",
                    style = LocalTextStyle.current.copy(
                        color = WhiteGray,
                        fontSize = 12.sp
                    )
                )
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
package com.zagirlek.presentation.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zagirlek.rickandmortytest.R
import com.zagirlek.rickandmortytest.domain.model.CharacterGender

@Composable
fun GenderText(
    gender: CharacterGender,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(
                id = when(gender){
                    CharacterGender.FEMALE -> R.drawable.female_24
                    CharacterGender.MALE -> R.drawable.male_24
                    CharacterGender.GENDERLESS -> R.drawable.transgender_24
                    CharacterGender.UNKNOWN -> R.drawable.question_mark_24
                }
            ),
            contentDescription = "Gender"
        )
        Spacer(
            modifier = Modifier
                .width(4.dp)
        )
        Text(
            text = gender.toString()
        )
    }
}


@Preview(
    showBackground = true
)
@Composable
fun GenderTextPreview(modifier: Modifier = Modifier) {
    GenderText(
        gender = CharacterGender.MALE
    )
}
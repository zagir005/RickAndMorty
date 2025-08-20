package com.zagirlek.rickandmortytest.ui.elements

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

@Composable
fun SpeciesText(
    species: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(R.drawable.pets_24),
            contentDescription = "Species"
        )
        Spacer(
            modifier = Modifier
                .width(4.dp)
        )
        Text(
            text = species
        )
    }
}


@Preview(
    showBackground = true
)
@Composable
fun StatusTextPreview(modifier: Modifier = Modifier) {
    SpeciesText(
        species = "Human"
    )
}
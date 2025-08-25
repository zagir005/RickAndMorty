package com.zagirlek.presentation.screen.characters.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zagirlek.core.model.CharacterStatus

@Composable
fun AliveStatusIndicator(
    characterStatus: CharacterStatus,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = _root_ide_package_.com.zagirlek.presentation.theme.Dark,
                shape = AbsoluteRoundedCornerShape(topLeft = 12.dp)
            )
            .padding(vertical = 2.dp, horizontal = 4.dp)
    ){
        Row(
            modifier = Modifier
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = when(characterStatus){
                            CharacterStatus.ALIVE -> _root_ide_package_.com.zagirlek.presentation.theme.Green
                            CharacterStatus.DEAD -> _root_ide_package_.com.zagirlek.presentation.theme.Red
                            CharacterStatus.UNKNOWN -> _root_ide_package_.com.zagirlek.presentation.theme.Gray
                        },
                        shape = CircleShape
                    )
            )
            Spacer(
                modifier = Modifier
                    .size(width = 8.dp, height = 8.dp)
            )
            Text(
                text = characterStatus.toString(),
                color = _root_ide_package_.com.zagirlek.presentation.theme.White
            )
        }

    }
}



@Preview(
    showBackground = true
)
@Composable
private fun AliveStatusIndicatorPreview(modifier: Modifier = Modifier) {
    AliveStatusIndicator(CharacterStatus.ALIVE)
}
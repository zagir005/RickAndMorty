package com.zagirlek.rickandmortytest.ui.screen.characters.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zagirlek.rickandmortytest.domain.model.CharacterFilters
import com.zagirlek.rickandmortytest.domain.model.CharacterGender
import com.zagirlek.rickandmortytest.domain.model.CharacterStatus
import com.zagirlek.rickandmortytest.ui.theme.WhiteGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCharacterBottomSheet(
    currFilters: CharacterFilters,
    onDismiss: () -> Unit = { },
    onFilter: (CharacterFilters) -> Unit = { },
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) {
    var currFiltersState by remember { mutableStateOf(value = currFilters) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
    ) {

        Column(
            modifier = modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = currFiltersState.name ?: "",
                onValueChange = {
                    currFiltersState = currFiltersState.copy(name = it)
                },
                label = {
                    Text(text = "Name")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            TextField(
                value = currFiltersState.species ?: "",
                onValueChange = {
                    currFiltersState = currFiltersState.copy(species = it)
                },
                label = {
                    Text(text = "Species")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            LabelBox(
                label = "Status",
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                EnumFiltersChip(
                    selectedItem = currFiltersState.status,
                    options = CharacterStatus.entries.toTypedArray(),
                    modifier = Modifier
                        .fillMaxWidth(),
                ) { selectedStatus ->
                    currFiltersState = currFiltersState.copy(status = selectedStatus)
                }
            }

            LabelBox(
                label = "Gender",
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                EnumFiltersChip(
                    selectedItem = currFiltersState.gender,
                    options = CharacterGender.entries.toTypedArray(),
                    modifier = Modifier
                        .fillMaxWidth()
                ) { selectedGender ->
                    currFiltersState = currFiltersState.copy(gender = selectedGender)
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        onFilter(CharacterFilters())
                    },
                    modifier = modifier
                        .weight(1f)
                ) {
                    Text(text = "Сбросить")
                }
                Button(
                    onClick = {
                        onFilter(currFiltersState)
                    },
                    modifier = modifier
                        .weight(1f)
                ) {
                    Text(text = "Применить")
                }
            }
        }
    }
}

@Composable
fun LabelBox(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = WhiteGray,
                shape = RoundedCornerShape(size = 8.dp)
            )
    ){
        Column(
            modifier = Modifier
                .padding(all = 6.dp)
        ) {
            Text(text = label)
            content()
        }
    }
}

@Composable
fun <T: Enum<T>>EnumFiltersChip(
    selectedItem: T?,
    options: Array<T>,
    modifier: Modifier = Modifier,
    onItemSelected: (T?) -> Unit
) {
    val selectedItemIndex = selectedItem?.let { options.indexOf(selectedItem) } ?: -1

    FlowRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = 6.dp)
    ) {
        options.forEachIndexed { index, item ->
            FilterChip(
                selected = index == selectedItemIndex,
                onClick = {
                    if (selectedItemIndex == index) onItemSelected(null) else onItemSelected(item)
                },
                label = {
                    Text(text = item.toString())
                },
                interactionSource = remember { MutableInteractionSource() },
                colors = FilterChipDefaults.filterChipColors()
            )
        }
    }
}

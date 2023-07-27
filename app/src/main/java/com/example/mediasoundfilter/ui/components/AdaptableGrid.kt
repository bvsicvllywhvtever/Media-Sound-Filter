package com.example.mediasoundfilter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.mediasoundfilter.ui.screens.media.MediaViewModel

@Composable
fun AdaptableGrid(rowSize: Int, sounds: List<String>, uploadViewModel: MediaViewModel) {

    val uploadUiState = uploadViewModel.mediaUiState.collectAsState()

    sounds.chunked(rowSize).forEach{ group ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            group.forEach { item ->
                Row(Modifier.weight(1f)){
                    RadioButton(
                        selected = (uploadUiState.value.selectedSounds[item] ?: false),
                        onClick = {
                            uploadViewModel.setSelectedSounds(item)
                        }
                    )
                    Text(item)
                }
            }

            if(group.size < rowSize){
                Box(Modifier.weight(1f))
            }
        }
    }
}
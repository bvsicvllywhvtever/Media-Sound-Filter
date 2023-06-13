package com.example.mediasoundfilter.screens.media

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediasoundfilter.R

@Composable
fun MediaScreen() {
    Column() {
        //media screen
        Text(
            text = "insert dynamic content here"
        )
        Text(
            text = stringResource(R.string.mute_sounds)
        )
        //for each loop; some sort of flexbox type layout where only two blocks will populate
        //each row
        val triggerSounds = stringArrayResource(R.array.trigger_sounds)
        for(i in triggerSounds.indices) {
            Row() {
                RadioButton(
                    selected = false,
                    onClick = {}
                )
                Text(triggerSounds.getOrNull(i).toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediaScreenPreview() {
    MediaScreen()
}
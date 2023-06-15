package com.example.mediasoundfilter.screens.media

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.nav.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaScreen(navRoutes: Array<() -> Unit>) {
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
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
                for (i in triggerSounds.indices) {
                    Row() {
                        RadioButton(
                            selected = false,
                            onClick = {}
                        )
                        Text(triggerSounds.getOrNull(i).toString())
                    }
                }
            }
        },
        bottomBar = {NavBar(navRoutes)}
    )
}

@Preview(showBackground = true)
@Composable
fun MediaScreenPreview() {
    MediaScreen(arrayOf())
}
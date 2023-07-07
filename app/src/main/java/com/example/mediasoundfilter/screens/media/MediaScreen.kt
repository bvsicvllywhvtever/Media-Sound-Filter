package com.example.mediasoundfilter.screens.media

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.nav.NavBar
import com.example.mediasoundfilter.youtube.YoutubePlayerComposeView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaScreen(navRoutes: Array<() -> Unit>) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                YoutubePlayerComposeView(videoId = "pBk4NYhWNMM") //check if this can have a content desc

                Column(
                    modifier = Modifier.padding(10.dp, 5.dp)
                ) {
                    Text(
                        text = "insert dynamic content here with multiple lines",
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Uploaded By Test",
                        fontSize = 16.sp
                    )
                }
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.mute_sounds),
                        fontSize = 20.sp
                    )
                    //for each loop; some sort of flexbox type layout where only two blocks will populate
                    //each row
                    val triggerSounds = stringArrayResource(R.array.trigger_sounds)
                    for (i in triggerSounds.indices) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = false,
                                onClick = {},
                                modifier = Modifier.scale(.75f)
                            )
                            Text(triggerSounds.getOrNull(i).toString())
                        }
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
    MediaScreen(arrayOf({}, {}, {}))
}
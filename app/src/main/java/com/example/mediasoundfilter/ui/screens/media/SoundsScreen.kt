package com.example.mediasoundfilter.ui.screens.media

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.ui.components.AdaptableGrid
import com.example.mediasoundfilter.ui.components.ExpandableCard
import com.example.mediasoundfilter.ui.components.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundsScreen(mediaViewModel: MediaViewModel, navController: NavController) {

    val mediaUiState = mediaViewModel.mediaUiState.collectAsState()
    val videoId = mediaUiState.value.videoId

    mediaViewModel.setSounds()

    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Text(stringResource(R.string.mute_sounds_title) + " " + "placeholder" + "?")

                val soundCategories = mediaUiState.value.muteSounds.keys
                for (cat in soundCategories) {
                    ExpandableCard(cat) {
                        mediaUiState.value.muteSounds[cat]?.let {
                            AdaptableGrid(
                                2,
                                it,
                                mediaViewModel
                            )
                        }
                    }
                }

                Button(
                    content = { Text(stringResource(R.string.watch_video)) },
                    onClick = {
                        navController.navigate("media/$videoId")
                        mediaViewModel.resetState()
                    }
                )
            }
        },
        bottomBar = {NavBar(navController)}
    )
}

@Preview(showBackground = true)
@Composable
fun SoundsScreenPreview(){
    SoundsScreen(viewModel(), rememberNavController())
}
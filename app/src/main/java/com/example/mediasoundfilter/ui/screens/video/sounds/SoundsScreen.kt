package com.example.mediasoundfilter.ui.screens.video.sounds

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
import com.example.mediasoundfilter.domain.model.Video
import com.example.mediasoundfilter.ui.components.AdaptableGrid
import com.example.mediasoundfilter.ui.components.ExpandableCard
import com.example.mediasoundfilter.ui.components.NavBar
import com.example.mediasoundfilter.ui.screens.video.VideoSharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundsScreen(videoSharedViewModel: VideoSharedViewModel,
                 soundsViewModel: SoundsViewModel,
                 navController: NavController) {

    val soundsUiState = soundsViewModel.soundsUiState.collectAsState()
    val video: Video = videoSharedViewModel.getCurrentVideo()!!

    soundsViewModel.setSounds()

    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {

                val title = video.title

                Text(stringResource(R.string.mute_sounds_title) + " " + title + "?")

                val soundCategories = soundsUiState.value.muteSounds
                for (cat in soundCategories) {
                    ExpandableCard(cat.category) {
                        AdaptableGrid(
                            2,
                            cat.sounds,
                            soundsViewModel
                        )
                    }
                }

                Button(
                    content = { Text(stringResource(R.string.watch_video)) },
                    onClick = {
                        navController.navigate("media")
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
    SoundsScreen(viewModel(), viewModel(), rememberNavController())
}
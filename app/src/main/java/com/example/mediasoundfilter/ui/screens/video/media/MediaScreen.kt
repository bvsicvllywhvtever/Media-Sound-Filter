package com.example.mediasoundfilter.ui.screens.video.media

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.ui.components.NavBar
import com.example.mediasoundfilter.ui.components.YoutubePlayerComposeView
import com.example.mediasoundfilter.ui.screens.video.VideoSharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaScreen(
    videoSharedViewModel: VideoSharedViewModel,
    mediaViewModel: MediaViewModel,
    navController: NavController) {

    val video = videoSharedViewModel.getCurrentVideo()!!

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                YoutubePlayerComposeView(video.id, mediaViewModel.getMuteTimes()) //check if this can have a content desc

                Column(
                    modifier = Modifier.padding(10.dp, 5.dp)
                ) {
                    Text(
                        text = video.title,
                        fontSize = 20.sp
                    )
                    Text(
                        text = video.channelTitle,
                        fontSize = 16.sp
                    )
                }
            }
        },
        bottomBar = { NavBar(navController) }
    )
}

@Preview(showBackground = true)
@Composable
fun MediaScreenPreview() {
    MediaScreen(viewModel(), viewModel(), rememberNavController())
}
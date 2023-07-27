package com.example.mediasoundfilter.ui.screens.upload

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
fun SoundsScreen(uploadViewModel: UploadViewModel, navController: NavController) {

    val uploadUiState = uploadViewModel.uploadUiState.collectAsState()
    val videoId = uploadUiState.value.videoId

    uploadViewModel.setSounds()

    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Text(stringResource(R.string.mute_sounds_title) + " " + "placeholder" + "?")

                val soundCategories = uploadUiState.value.muteSounds.keys
                for (cat in soundCategories) {
                    ExpandableCard(cat) {
                        uploadUiState.value.muteSounds[cat]?.let {
                            AdaptableGrid(
                                2,
                                it,
                                uploadViewModel
                            )
                        }
                    }
                }

                Button(
                    content = { Text(stringResource(R.string.watch_video)) },
                    onClick = {
                        navController.navigate("media/$videoId")
                        uploadViewModel.resetState()
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
package com.example.mediasoundfilter.ui.screens.video.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.ui.components.NavBar
import com.example.mediasoundfilter.ui.screens.error.BottomErrorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(uploadViewModel: UploadViewModel,
                 navController: NavHostController) {

    val uploadUiState = uploadViewModel.uploadUiState.collectAsState()

    Scaffold(
        content = { padding ->
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.upload_message),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(50.dp, 0.dp)
                )
                var linkValue by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = linkValue,
                    onValueChange = {linkValue = it},
                    label = {Text(stringResource(R.string.link))},
                    leadingIcon = {
                                  Image(
                                      painter = painterResource(R.drawable.link),
                                      contentDescription = null,
                                      colorFilter = ColorFilter.tint(colorResource(R.color.main))
                                  )
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(20.dp)
                )
                Button(
                    onClick = {
                        uploadViewModel.extractVideoId(linkValue)
                        navController.navigate("loading")
                              },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.main))
                ) {
                    Text(stringResource(R.string.upload))
                }
                uploadUiState.value.linkError?.let{ BottomErrorText(it) }
            }
        },
        bottomBar = { NavBar(navController) }
    )
}

@Preview(showBackground = true)
@Composable
fun UploadScreenPreview() {
    UploadScreen(viewModel(), rememberNavController())
}
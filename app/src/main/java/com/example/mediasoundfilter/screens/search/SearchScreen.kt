package com.example.mediasoundfilter.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.nav.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navRoutes: Array<() -> Unit>) {
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Row() {
                    Image(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = stringResource(R.string.arrow)
                    )
                    TextField(
                        value = "",
                        onValueChange = {}
                    )
                    Image(
                        painter = painterResource(R.drawable.search),
                        contentDescription = stringResource(R.string.search)
                    )
                }
                //foreach suggestion
            }
        },
        bottomBar = {NavBar(navRoutes)}
    )
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(arrayOf({}, {}, {}))
}
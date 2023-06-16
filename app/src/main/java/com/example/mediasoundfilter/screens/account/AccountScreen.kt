package com.example.mediasoundfilter.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.nav.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navRoutes: Array<() -> Unit>, navigateToMedia: () -> Unit) {
    Scaffold(
        topBar = {},
        content = { padding ->
            Column(
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, padding.calculateBottomPadding())
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    text = "username101",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.saved),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 5.dp)
                )
                //dynamic block
                LazyColumn() {
                    items(30){
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable(
                                    onClick = navigateToMedia
                                )
                        ){
                            Image(
                                painter = painterResource(R.drawable.thumbnail),
                                contentDescription = "make sure to change this",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(15.dp))
                                    .weight(1f)
                            )
                            Column(
                                modifier = Modifier.weight(2f)
                            ) {
                                Text(
                                    //limit 50 chars
                                    text = "A Test Youtube Video Title That Is Much Longer Than The Last",
                                    fontSize = 14.sp,
                                    lineHeight = 15.sp,
                                    modifier = Modifier
                                        .padding(10.dp, 0.dp)
                                )
                                Text(
                                    text = "Uploaded by Test",
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(10.dp, 0.dp)
                                )
                                Text(
                                    text = "1 month ago",
                                    fontSize = 12.sp,
                                    modifier = Modifier
                                        .padding(10.dp, 0.dp)
                                )
                            }
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
fun AccountScreenPreview() {
    AccountScreen(arrayOf({}, {}, {}), {})
}
package com.example.mediasoundfilter.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediasoundfilter.R
import com.example.mediasoundfilter.nav.NavBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, padding.calculateBottomPadding())
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.05f)
                ) {
                    Image(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = stringResource(R.string.arrow),
                        colorFilter = ColorFilter.tint(colorResource(R.color.main)),
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.FillHeight
                    )
                    var searchVal by remember {mutableStateOf("")}
                    BasicTextField(
                        value = searchVal,
                        onValueChange = {searchVal = it},
                        textStyle = TextStyle.Default.copy(fontSize = 12.sp),
                        modifier = Modifier.fillMaxHeight(),
                        singleLine = true,
                        decorationBox = {innerTextField ->
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(.8f)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(size = 16.dp)
                                    )
                                    .padding(10.dp)
                            ){
                                innerTextField()
                            }
                        }
                    )
                    Image(
                        painter = painterResource(R.drawable.search),
                        contentDescription = stringResource(R.string.search),
                        colorFilter = ColorFilter.tint(colorResource(R.color.main)),
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                LazyColumn(
                    //horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp)
                ){
                    items(50){
                        Row(
                            modifier = Modifier.padding(10.dp, 17.5.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = colorResource(R.color.main)
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Test Result String",
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {NavBar(navController)}
    )
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(rememberNavController())
}
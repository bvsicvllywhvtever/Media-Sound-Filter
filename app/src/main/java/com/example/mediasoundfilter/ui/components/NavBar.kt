package com.example.mediasoundfilter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediasoundfilter.R

@Composable
fun NavBar(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.main))
            .fillMaxHeight(.05f)
            //.padding(0.dp, 40.dp)
            //.size(100.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.account),
            contentDescription = stringResource(R.string.account),
            modifier = Modifier
                .fillMaxHeight(.75f)
                .align(Alignment.CenterVertically)
                .clickable(onClick = {navController.navigate("account")}),
            contentScale = ContentScale.FillHeight
        )
        Divider(
            color = colorResource(R.color.secondary),
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Image(
            painter = painterResource(R.drawable.upload),
            contentDescription = stringResource(R.string.upload),
            modifier = Modifier
                .fillMaxHeight(.75f)
                .align(Alignment.CenterVertically)
                .clickable(onClick = {navController.navigate("upload")}),
            contentScale = ContentScale.FillHeight
        )
        Divider(
            color = colorResource(R.color.secondary),
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Image(
            painter = painterResource(R.drawable.search),
            contentDescription = stringResource(R.string.search),
            modifier = Modifier
                .fillMaxHeight(.75f)
                .align(Alignment.CenterVertically)
                .clickable(onClick = {navController.navigate("search")}),
            contentScale = ContentScale.FillHeight
        )
    }
}


@Preview(showBackground=true)
@Composable
fun NavBarPreview() {
    //NavBar()
}
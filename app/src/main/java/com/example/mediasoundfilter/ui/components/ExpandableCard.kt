package com.example.mediasoundfilter.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableCard(
    title: String,
    dropdownContent: @Composable () -> Unit
) {
    var expanded by remember {mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .animateContentSize()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(title)
            IconButton(onClick = {expanded = !expanded}){
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(if(expanded) 180f else 0f)
                )
            }
        }

        if(expanded){
            dropdownContent()
        }

    }
}
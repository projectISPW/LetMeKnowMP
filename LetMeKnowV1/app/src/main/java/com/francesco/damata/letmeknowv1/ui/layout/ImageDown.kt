package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.francesco.damata.letmeknowv1.R

@Composable
fun myImage(idInp:Int){
    Image(
        painter = painterResource(idInp),
        contentDescription ="",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(300.dp)
            .height(300.dp)
    )
}
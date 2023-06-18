package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.ui.theme.button

@Composable
fun Button(where:LetMeKnowScreen, content: String){
    Button(onClick = {
        ScreenRouter.navigateTo(where)
        }, colors = ButtonDefaults.textButtonColors(
        backgroundColor = MaterialTheme.colors.button
        )){
        Text(content,color = Color.White,fontSize = 15.sp)
    }
}

package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
@Composable
fun InputTraits(locked:Boolean) {
    val empSlider = rememberSaveable() {
        mutableStateOf(1f)
    }
    val humSlider = rememberSaveable() {
        mutableStateOf(1f)
    }
    val optSlider = rememberSaveable() {
        mutableStateOf(1f)
    }
    Column() {
        Traits("emotional",empSlider,locked)
        Traits("lively", humSlider, locked)
        Traits("optimistic", optSlider, locked)
    }
}
@Composable
fun Traits(wich: String, trait: MutableState<Float>, locked: Boolean){
    Row() {
        Text(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding( top = 13.dp,start=20.dp)
                .width(250.dp),

            text = wich+" :    "+trait.value.roundToInt().toString())
        if(locked){
            Slider(value = trait.value,
                onValueChange = {},
                valueRange = 1f..5f,
                colors= SliderDefaults.colors(Color.Blue),
                modifier = Modifier
                    .padding( start = 40.dp,end=200.dp),
            )
        }else{
            Slider(value = trait.value,
                onValueChange = { trait.value = it },
                valueRange = 1f..5f,
                colors= SliderDefaults.colors(Color.Blue),
                modifier = Modifier
                    .padding( start = 40.dp,end=200.dp),
            )
        }

    }
}
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import kotlin.math.roundToInt
@Composable
fun InputTraits(locked:Boolean,myModelScreen :MyModelScreen) {
    val empSlider = rememberSaveable() {
        mutableStateOf(myModelScreen.userClass.emotional.toFloat())
    }
    val humSlider = rememberSaveable() {
        mutableStateOf(myModelScreen.userClass.lively.toFloat())
    }
    val optSlider = rememberSaveable() {
        mutableStateOf(myModelScreen.userClass.optimistic.toFloat())
    }
    Column() {
        Traits(stringResource(R.string.emotional),empSlider,locked)
        Traits(stringResource(R.string.lively), humSlider, locked)
        Traits(stringResource(R.string.optimism), optSlider, locked)
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
                .width(180.dp),

            text = wich+" :    "+trait.value.roundToInt().toString())
        if(locked){
            Slider(value = trait.value,
                onValueChange = {},
                valueRange = 1f..5f,
                colors= SliderDefaults.colors(Color.Blue),
                modifier = Modifier
                    .padding( start = 40.dp,end=5.dp),
            )
        }else{
            Slider(value = trait.value,
                onValueChange = { trait.value = it },
                valueRange = 1f..5f,
                colors= SliderDefaults.colors(Color.Blue),
                modifier = Modifier
                    .padding( start = 40.dp,end=5.dp),
            )
        }

    }
}
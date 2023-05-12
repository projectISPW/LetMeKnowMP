package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.myBlue
import kotlin.math.roundToInt

@Composable
fun HomeUsr(myModelScreen: MyModelScreen){
    profColumn()
}

@Composable
fun profColumn(){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.myBlue)
        {
            Text(stringResource(R.string.topBarHome),color = Color.White,fontSize = 24.sp)
        }
        Text(text = stringResource(R.string.profile),color = Color.Black,fontSize = 40.sp)
        Text(text = "User #00000",color = MaterialTheme.colors.myBlue,fontSize = 20.sp)         //Al posto degli 00000 ci va l'userid
        HomeTraits()
    }
}

@Composable
fun HomeTraits() {
    val emphaty : Float = 1f
    val humor : Float = 1f
    val optimism : Float = 1f           //Questi valori saranno poi da caricare dal db
    Column() {
        NotMutableTraits("emotional",emphaty)
        NotMutableTraits("lively",humor)
        NotMutableTraits("optimism",optimism)
    }
}
@Composable
fun NotMutableTraits(wich:String,trait : Float){
    Row() {
        Text(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 13.dp, start = 20.dp)
                .width(170.dp),

            text = wich+" :    "+trait.roundToInt().toString())
        Slider(value = trait,
            onValueChange = {},
            valueRange = 1f..5f,
            colors= SliderDefaults.colors(Color.Blue),
            modifier = Modifier
                .padding( start = 40.dp,end=5.dp),
        )
    }
}



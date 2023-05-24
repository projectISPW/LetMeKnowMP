package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.button
import com.francesco.damata.letmeknowv1.ui.theme.myBlue
import kotlin.math.roundToInt

@Composable
fun SearchUser(myModelScreen: MyModelScreen) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TopAppBar(
            {
                IconButton(onClick = {
                    ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)         //Ricambia poi con il login
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                }
                IconButton(onClick = {
                    ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    stringResource(R.string.searchUserTopBar),
                    color = Color.White,
                    fontSize = 24.sp
                )
            })
        Text(text = stringResource(R.string.SrcByUsr), color =  MaterialTheme.colors.myBlue, fontSize = 25.sp)
        SearchSlider(myModelScreen)
        Button(onClick = {
            ScreenRouter.navigateTo(LetMeKnowScreen.SearchResult)
        }, colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.button
        )){
            Text(stringResource(R.string.Confirm),color = Color.White,fontSize = 20.sp)
        }
        myImage(R.drawable.search)
    }
}

@Composable
fun SearchSlider(myModelScreen: MyModelScreen){
    val context= LocalContext.current
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
            SearchTraits(context.getString(R.string.emotional),empSlider,myModelScreen)
            SearchTraits(context.getString(R.string.lively),humSlider,myModelScreen)
            SearchTraits( context.getString(R.string.optimism),optSlider,myModelScreen)
        }
    }

@Composable
fun SearchTraits(wich:String,trait : MutableState<Float>,myModelScreen: MyModelScreen){
    Row() {
        val context= LocalContext.current
        Text(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding( top = 13.dp,start=20.dp)
                .width(170.dp),

            text = wich+" :    "+trait.value.roundToInt().toString())
        Slider(value = trait.value,
            onValueChange = { trait.value = it },
            valueRange = 1f..5f,
            colors= SliderDefaults.colors(Color.Blue),
            modifier = Modifier
                .padding( start = 40.dp,end=5.dp)
        )
        when(wich) {             ///Non scrive su myModelScreen
            context.getString(R.string.emotional) -> myModelScreen.onSearchEmo = trait.value.roundToInt()
            context.getString(R.string.lively) -> myModelScreen.onSearchLv = trait.value.roundToInt()
            context.getString(R.string.optimism) -> myModelScreen.onSearchOpt = trait.value.roundToInt()
        }
    }
}
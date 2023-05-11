package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.myBlue

@Composable
fun HomeUsr(myModelScreen: MyModelScreen){
    val emphathy = rememberSaveable() {
        mutableStateOf("")
    }
    val humor = rememberSaveable() {
        mutableStateOf("")
    }
    val optimism = rememberSaveable(){
        mutableStateOf("")
    }
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

    }

}



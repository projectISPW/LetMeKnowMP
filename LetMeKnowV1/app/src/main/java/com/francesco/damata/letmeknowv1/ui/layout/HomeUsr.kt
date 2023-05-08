package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.screen.MyModelScreen


@Composable
fun HomeUsr(myModelScreen: MyModelScreen){
    var passwordVisibility=false
    val user = rememberSaveable() {
        mutableStateOf("")
    }
    val password = rememberSaveable() {
        mutableStateOf("")
    }
    val tempCover = rememberSaveable(){
        mutableStateOf("")
    }
    val tempunCover = rememberSaveable(){
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TopAppBar(
            {
                Text("Home",color = Color.White,fontSize = 24.sp)
            })
        Spacer(modifier = Modifier.height(16.dp))
        Icon(imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier
                .size(size = 120.dp)
                .border(1.dp, Color.Blue, shape = CircleShape),
            tint = Color.Blue

        )

        TextField(
            value = user.value,
            onValueChange = {
                user.value = it
            }, textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),

            label = {
                Text("User:", fontSize = 24.sp)
            }
        )


    }

}
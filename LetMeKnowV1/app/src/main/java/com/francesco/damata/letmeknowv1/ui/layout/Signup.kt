package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.screen.MyModelScreen

@Composable
fun Signup(myModelScreen: MyModelScreen){
    val email = rememberSaveable() {
        mutableStateOf("")
    }
    val pswd = rememberSaveable() {
        mutableStateOf("")
    }
    val confirmPswd = rememberSaveable() {
        mutableStateOf(false )
    }
    val empSlider = rememberSaveable() {
        mutableStateOf(false )
    }
    val humSlider = rememberSaveable() {
        mutableStateOf(false )
    }
    val optSlider = rememberSaveable() {
        mutableStateOf(false )
    }
    Column(
        modifier = Modifier
            //.padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TopAppBar(
            {
                Text("Signup",color = Color.White,fontSize = 24.sp)
            }
        )

    }


}

package com.francesco.damata.letmeknowv1.ui.layout

import android.graphics.Color.parseColor
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.button
import com.francesco.damata.letmeknowv1.ui.theme.letMeKnowColor
import com.francesco.damata.letmeknowv1.ui.theme.myBlue


@Composable
fun MainLayout(myModelScreen: MyModelScreen) {
    Column(
        modifier = Modifier
            //.padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.myBlue)
        {
            Text("Login", color = Color.White, fontSize = 24.sp)
        }
        ColUnderTheTop()
        InputUsr()
    }
}
@Composable
fun ColUnderTheTop(){
    Column(
        modifier = Modifier
            //.padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Let me know",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.letMeKnowColor
        )
        //Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = Icons.Default.Groups,
            contentDescription = null,
            modifier = Modifier
                .size(size = 150.dp),
            //.border(10.dp, Color.Blue, shape = CircleShape),
            tint = "#8290f1".color,
            )
        Text(
            text = "signup for free",
            fontSize = 24.sp,
            color = "#377dff".color,
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            ScreenRouter.navigateTo(LetMeKnowScreen.Signup)
                        },
                        onTap = {
                            println("on tap")
                        },
                        onDoubleTap = {
                            println("on double tap")
                        },
                        onLongPress = {
                            println("on long press")
                        }
                    )
                }
        )
    }
}
@Composable
fun InputUsr(){
    val user = rememberSaveable() {
        mutableStateOf("")
    }
    val password = rememberSaveable() {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable() {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            //.padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(
            value = user.value,
            onValueChange = {
                user.value = it
            }, textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),

            label = {
                Text("User:", fontSize = 24.sp)
            }
        )

        //Spacer(modifier = Modifier.height(16.dp))
        //video https://www.youtube.com/watch?v=eNAhOqF83Kg
        TextField(value = password.value,
            onValueChange = {
                password.value=it
            },
            textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),
            label = {
                Text("Password:", fontSize = 24.sp)
            },
            trailingIcon={
                IconButton(onClick={passwordVisibility.value=!passwordVisibility.value}){
                    if(passwordVisibility.value){
                        Icon(
                            imageVector = Icons.Default.Visibility,//https://fonts.google.com/icons
                            contentDescription = "Visibility Icon"

                        )
                    }else{
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = "Visibility Icon"

                        )
                    }

                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if(passwordVisibility.value)VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (user.value=="123456" && password.value=="password"){
                ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
            }
        }, colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.button

        )) {
            Text("Verify",color = Color.White,fontSize = 24.sp)
        }
    }


}
val String.color
    get()=Color(parseColor(this))
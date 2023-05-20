package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.button
import com.francesco.damata.letmeknowv1.ui.theme.letMeKnowColor
import kotlin.math.roundToInt

@Composable
fun Signup(myModelScreen: MyModelScreen) {


    Column(
        modifier = Modifier
            //.padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TopAppBar(
            {
                IconButton({
                    ScreenRouter.navigateTo(LetMeKnowScreen.Login)
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
                Text(
                    "Signup", color = Color.White, fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, top = 10.dp)
                )
            }
        )
        Text(
            text = "Let me know",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.letMeKnowColor
        )
        InputData()
        InputTraits(false,myModelScreen)
        Button(onClick = { }, colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.button
        )) {
            Text("Confirm",color = Color.White,fontSize = 24.sp)
        }
    }
}

    @Composable
    fun InputData(){
        val email = rememberSaveable() {
            mutableStateOf("")
        }
        val pswd = rememberSaveable() {
            mutableStateOf("")
        }
        val confirmPswd = rememberSaveable() {
            mutableStateOf("")
        }
        val passwordVisibility = rememberSaveable() {
            mutableStateOf(false)
        }
       Column() {
           TextField(value = email.value,
               onValueChange = {
                   email.value=it
               },
               textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),
               label = {
                   Text(stringResource(R.string.email), fontSize = 24.sp)
               },
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
               modifier=Modifier.width(300.dp)
           )
           Spacer(modifier = Modifier.height(16.dp))


           TextField(value = pswd.value,
               onValueChange = {
                   pswd.value=it
               },
               textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),
               modifier=Modifier.width(300.dp),
               label = {
                   Text(stringResource(R.string.pswd), fontSize = 24.sp)
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
               else PasswordVisualTransformation())



           TextField(value = confirmPswd.value,
               onValueChange = {
                   confirmPswd.value=it
               },
               modifier=Modifier.width(300.dp),
               textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),
               label = {
                   Text(stringResource(R.string.confirm_pswd), fontSize = 24.sp)
               },
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
               visualTransformation = if(passwordVisibility.value)VisualTransformation.None
               else PasswordVisualTransformation())

       }
    }






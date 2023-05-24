package com.francesco.damata.letmeknowv1.ui.layout

import android.app.Application
import android.content.Context
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.db.User
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.*
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModelFactory
import com.francesco.damata.letmeknowv1.viewModel.UserViewModel
import com.francesco.damata.letmeknowv1.viewModel.UserViewModelFactory


@Composable

fun MainLayout(myModelScreen: MyModelScreen) {
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
                IconButton({}) {
                    Icon(
                        imageVector = Icons.Default.Groups,
                        contentDescription = stringResource(R.string.user)
                    )
                }
                Text(stringResource(R.string.Signup),color = Color.White,fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, top = 10.dp))
            })
        ColUnderTheTop()
        InputUsr(myModelScreen =myModelScreen )
        signupText()

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
            text = stringResource(R.string.app_name),
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
            tint = MaterialTheme.colors.icon,
            )

    }
}
@Composable
fun signupText(){
    Text(
        text = stringResource(R.string.SignUpFree),
        fontSize = 24.sp,
        color = "#377dff".color,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        ScreenRouter.navigateTo(LetMeKnowScreen.Signup)
                    }
                )
            }
    )
}
@Composable
fun InputUsr(myModelScreen: MyModelScreen){
    val password = rememberSaveable() {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable() {
        mutableStateOf(false)
    }
    var context:Context= LocalContext.current
    val viewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context.applicationContext as Application)
    )
    Column(
        modifier = Modifier
            //.padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(
            value = myModelScreen.usr,
            onValueChange = {
                myModelScreen.usr= it
            }, textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),
            modifier=Modifier.width(300.dp),
            label = {
                Text(stringResource(R.string.user), fontSize = 24.sp)
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
                Text(stringResource(R.string.pswd), fontSize = 24.sp)
            },
            modifier=Modifier.width(300.dp),
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
        var user =viewModel.getLogin( myModelScreen.usr,password.value).observeAsState().value
        Button(onClick = {
            if (user!=null){
                ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
                myModelScreen.userClass=user
                myModelScreen.usr=user.userid
            }
        }, colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.button

        )) {
            Text(stringResource(R.string.confirm),color = Color.White,fontSize = 24.sp)
        }
    }


}

package com.francesco.damata.letmeknowv1.ui.layout

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.button
import com.francesco.damata.letmeknowv1.viewModel.UserViewModel
import com.francesco.damata.letmeknowv1.viewModel.UserViewModelFactory
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
        Traits(stringResource(R.string.emotional),empSlider,myModelScreen,locked)
        Traits(stringResource(R.string.lively), humSlider,myModelScreen,locked)
        Traits(stringResource(R.string.optimism), optSlider, myModelScreen,locked)
    }
}
@Composable
fun Traits(wich: String, trait: MutableState<Float>,myModelScreen: MyModelScreen?=null,locked: Boolean){
    Row() {
        val context= LocalContext.current
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
            var user:com.francesco.damata.letmeknowv1.db.User?= myModelScreen?.userClass
            val viewModel: UserViewModel = viewModel(
                factory = UserViewModelFactory(context.applicationContext as Application)
            )
            Button(onClick = {
              if(user!=null){
                  when(wich) {
                      context.getString(R.string.emotional)->user.emotional=trait.value.toInt()
                      context.getString(R.string.lively)->user.lively=trait.value.toInt()
                      context.getString(R.string.optimism)->user.optimistic=trait.value.toInt()
                  }
                  viewModel.update(user)
              }
            }, colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.button
            )) {
                Text("Confirm",color = Color.White,fontSize = 24.sp)
            }
        }

    }
}
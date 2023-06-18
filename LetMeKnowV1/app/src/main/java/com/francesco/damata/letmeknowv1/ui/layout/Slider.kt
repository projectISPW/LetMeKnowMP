package com.francesco.damata.letmeknowv1.ui.layout

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.button
import com.francesco.damata.letmeknowv1.viewModel.UserViewModel
import com.francesco.damata.letmeknowv1.viewModel.UserViewModelFactory
import kotlin.math.roundToInt
@Composable
fun InputTraits(locked:Boolean,myModelScreen :MyModelScreen) {

    val empSlider:MutableState<Float>
    val humSlider:MutableState<Float>
    val optSlider:MutableState<Float>
    if(myModelScreen.onSearch || myModelScreen.fromChat && locked){
        empSlider = rememberSaveable {
            mutableStateOf(myModelScreen.usrVisit.emotional.toFloat())
        }
        humSlider = rememberSaveable {
            mutableStateOf(myModelScreen.usrVisit.lively.toFloat())
        }
        optSlider = rememberSaveable {
            mutableStateOf(myModelScreen.usrVisit.optimistic.toFloat())
        }
    }
    else{
        empSlider = rememberSaveable {
            mutableStateOf(myModelScreen.userClass.emotional.toFloat())
        }
        humSlider = rememberSaveable{
            mutableStateOf(myModelScreen.userClass.lively.toFloat())
        }
        optSlider = rememberSaveable{
            mutableStateOf(myModelScreen.userClass.optimistic.toFloat())
        }
    }


    Column {
        val context= LocalContext.current
        val viewModel: UserViewModel = viewModel(
            factory = UserViewModelFactory(context.applicationContext as Application)
        )
        val user=viewModel.getLogin(myModelScreen.userClass.userid,myModelScreen.userClass.password).observeAsState().value
        if (user != null) {
            myModelScreen.userClass=user
        }
        Traits(stringResource(R.string.emotional), empSlider, myModelScreen, locked)
        Traits(stringResource(R.string.lively), humSlider, myModelScreen, locked)
        Traits(stringResource(R.string.optimistic), optSlider, myModelScreen, locked)
        if(!locked){
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier=Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                viewModel.update(myModelScreen.userClass)
                ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
                },
                colors = ButtonDefaults.textButtonColors(
                 backgroundColor = MaterialTheme.colors.button
                )
            ) {
                Text(stringResource(R.string.confirm),color = Color.White,fontSize = 24.sp)
            }

        }
    }
    
}
@Composable
fun Traits(which: String, trait: MutableState<Float>, myModelScreen: MyModelScreen?=null, locked: Boolean){
    Row {
        val context=LocalContext.current
        Text(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 13.dp, start = 20.dp)
                .width(185.dp),

            text = which+"  :    "+trait.value.roundToInt().toString())
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
            if(myModelScreen?.userClass!=null){
                when(which){
                    context.getString(R.string.emotional)->myModelScreen.userClass.emotional=trait.value.roundToInt()
                    context.getString(R.string.lively)->myModelScreen.userClass.lively=trait.value.roundToInt()
                    context.getString(R.string.optimistic)->myModelScreen.userClass.optimistic=trait.value.roundToInt()
                }
            }
        }

    }
}
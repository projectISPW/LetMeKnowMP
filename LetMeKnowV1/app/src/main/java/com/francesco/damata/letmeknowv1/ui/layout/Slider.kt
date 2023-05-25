package com.francesco.damata.letmeknowv1.ui.layout

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
        val empSlider = rememberSaveable() {
            mutableStateOf(myModelScreen.userClass.emotional.toFloat())
        }
        val humSlider = rememberSaveable() {
            mutableStateOf(myModelScreen.userClass.lively.toFloat())
        }
        val optSlider = rememberSaveable() {
            mutableStateOf(myModelScreen.userClass.optimistic.toFloat())
        }
    val empVisitSlider = rememberSaveable() {
        mutableStateOf(myModelScreen.usrVisit.emotional.toFloat())
    }
    val humVisitSlider = rememberSaveable() {
        mutableStateOf(myModelScreen.usrVisit.lively.toFloat())
    }
    val optVisitSlider = rememberSaveable() {
        mutableStateOf(myModelScreen.usrVisit.optimistic.toFloat())
    }
    Column() {
        val context= LocalContext.current
        val viewModel: UserViewModel = viewModel(
            factory = UserViewModelFactory(context.applicationContext as Application)
        )
        var user=viewModel.getLogin(myModelScreen.userClass.userid,myModelScreen.userClass.password).observeAsState().value
        if (user != null) {
            myModelScreen.userClass=user
        }
        if(!myModelScreen.onVisitUserClass) {
            Traits(stringResource(R.string.emotional), empSlider, myModelScreen, locked)
            Traits(stringResource(R.string.lively), humSlider, myModelScreen, locked)
            Traits(stringResource(R.string.optimism), optSlider, myModelScreen, locked)
        }else {
            Traits(stringResource(R.string.emotional), empVisitSlider, myModelScreen, locked)
            Traits(stringResource(R.string.lively), humVisitSlider, myModelScreen, locked)
            Traits(stringResource(R.string.optimism), optVisitSlider, myModelScreen, locked)
        }
        if(!locked){
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
fun Traits(wich: String, trait: MutableState<Float>,myModelScreen: MyModelScreen?=null,locked: Boolean){
    Row() {
        val context=LocalContext.current
        Text(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 13.dp, start = 20.dp)
                .width(180.dp),

            text = wich+" .:    "+trait.value.roundToInt().toString())
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
                when(wich){
                    context.getString(R.string.emotional)->myModelScreen!!.userClass.emotional=trait.value.roundToInt()
                    context.getString(R.string.lively)->myModelScreen!!.userClass.lively=trait.value.roundToInt()
                    context.getString(R.string.optimism)->myModelScreen!!.userClass.optimistic=trait.value.roundToInt()
                }
            }
        }

    }
}
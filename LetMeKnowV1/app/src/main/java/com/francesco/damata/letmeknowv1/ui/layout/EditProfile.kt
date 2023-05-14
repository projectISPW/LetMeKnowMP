package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
fun EditProfile(myModelScreen: MyModelScreen){      //Passo come parametro i tratti attuali del profilo
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TopAppBar(
            {
                IconButton(onClick = {
                    ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.backbutton)
                    )
                }
                Text(stringResource(R.string.edprof), color = Color.White, fontSize = 24.sp)
            })
        Text(text = "Select your profile parameters ", color =  MaterialTheme.colors.myBlue, fontSize = 25.sp)
        InputTraits(false)
        Button(onClick = {
            //Manca parte di controllo che scrive i traits su db
            ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
        }, colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.button
        )){
            Text("Confirm",color = Color.White,fontSize = 20.sp)
        }
        myImageEditProf()
    }
}

@Composable
fun myImageEditProf(){
    Image(
        painter = painterResource(id = R.drawable.sleep_home),
        contentDescription ="",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(500.dp)
    )

}

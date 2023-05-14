package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

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
fun HomeUsr(myModelScreen: MyModelScreen){
    profColumn()
}

@Composable
fun profColumn(){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TopAppBar(
            {
                IconButton(onClick = {
                    ScreenRouter.navigateTo(LetMeKnowScreen.Login)
                }
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = stringResource(R.string.user)
                    )
                }
                Text(stringResource(R.string.topBarHome),color = Color.White,fontSize = 24.sp)
            })
        Text(text = stringResource(R.string.profile),color = Color.Black,fontSize = 40.sp)
        Text(text = "User #00000",color = MaterialTheme.colors.myBlue,fontSize = 30.sp)         //Al posto degli 00000 ci va $uid
        InputTraits(true)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
            EditButton()
            Spacer(modifier = Modifier.width(45.dp))
            RecentChatButton()
        }
        myImage()
    }
}
/*REUSE OF THE CODE

@Composable
fun HomeTraits() {
    val emphaty : Float = 1f
    val humor : Float = 1f
    val optimism : Float = 1f           //Questi valori saranno poi da caricare dal db
    Column {
        NotMutableTraits("emotional",emphaty)
        NotMutableTraits("lively",humor)
        NotMutableTraits("optimism",optimism)
    }
}

@Composable
fun NotMutableTraits(wich:String,trait : Float){
    Row{
        Text(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 13.dp, start = 20.dp)
                .width(170.dp),

            text = wich+" :    "+trait.roundToInt().toString())
        Slider(value = trait,
            onValueChange = {},
            valueRange = 1f..5f,
            colors= SliderDefaults.colors(Color.Blue),
            modifier = Modifier
                .padding( start = 40.dp,end=5.dp),
        )
    }
}
*/
@Composable
fun EditButton(){
    Button(onClick = {
            ScreenRouter.navigateTo(LetMeKnowScreen.EditProfile)
    }, colors = ButtonDefaults.textButtonColors(
        backgroundColor = MaterialTheme.colors.button
    )){
        Text("Edit Profile",color = Color.White,fontSize = 20.sp)
    }
}

@Composable
fun RecentChatButton(){
    Button(onClick = {
        ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)
    }, colors = ButtonDefaults.textButtonColors(
        backgroundColor = MaterialTheme.colors.button
    )){
        Text("Chat",color = Color.White,fontSize = 20.sp)
    }
}

@Composable
fun myImage(){
    Image(
        painter = painterResource(id = R.drawable.sleep_home),
        contentDescription ="",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(500.dp)
    )

}



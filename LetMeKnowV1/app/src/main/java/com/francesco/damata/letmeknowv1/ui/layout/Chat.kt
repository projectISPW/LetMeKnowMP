package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.letMeKnowColor

@Composable
fun Chat(myModelScreen: MyModelScreen) {
    Column(){
        TopAppBar(
            {
                IconButton({
                    ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
                IconButton(onClick = {
                    //ScreenRouter.navigateTo(LetMeKnowScreen.Visit)

                },
                    modifier = Modifier
                        .then(Modifier.size(70.dp))
                        .border(5.dp, MaterialTheme.colors.letMeKnowColor, shape = CircleShape)
                ) {
                    Icon(Icons.Default.Person,
                        contentDescription = "content description",
                        tint = "#8290f1".color,
                        modifier=Modifier
                            .size(100.dp)
                    )
                }
                Text(
                    "User #Jessie", color = Color.White, fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, top = 10.dp)
                )
            }
        )
        Conversation(MessageListChat)

    }
}
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(
        modifier=Modifier.height(500.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items=messages){
                message->
            MessageChat(message,"Francesco","Jessie")
        }
    }
}
@Composable
fun MessageChat(message:Message, user:String, sender:String){
    var msgExpanded= rememberSaveable() {
        mutableStateOf(false)
    }
    Row{
        if (message.sender==user) {
            Spacer(modifier = Modifier.width(100.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Spacer(modifier = Modifier.width(40.dp))
                Text(
                    message.reciver,
                    style = MaterialTheme.typography.body1,
                    fontSize = 24.sp,
                    maxLines = if (msgExpanded.value) Int.MAX_VALUE else 5,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .background(Color.Green)
                        .width(300.dp)


                )
            }
        }
        else{

                Surface(shape=MaterialTheme.shapes.medium,elevation =1.dp){
                Text(message.reciver,
                    style=MaterialTheme.typography.body1,
                    fontSize = 24.sp,
                    maxLines=if(msgExpanded.value)Int.MAX_VALUE else 5,
                    modifier=Modifier
                        .padding(start=10.dp)
                        .background(Color.Red)
                        .widthIn(100.dp,300.dp)
                )
            }

        }

    }
}
val MessageListChat=listOf(
    Message("Francesco","Really exited","Jessie"),
    Message("Jessie","Really exitedQ4VAEWFGWSFGVGW4STRWHTRSGHWT4ASEFVTG5QAERVTQR3EAVGQAEGQAEFGQAGRQACEQAERCQAEFGQAEFCGQAERGRQEAF","Francesco"),
    Message("Jessie","Really exited","Francesco"),
    Message("Francesco","Really exited","Jessie"),
    Message("Jessie","Really exited  GRRAWdfsCQWAGRZFE CGWAESZFDVGWVERASFVQAEGGWSF","Francesco"),
    Message("Jessie","Really exitedQ4VAEWFGWSFGVGW4STRWHTRSGHWT4ASEFVTG5QAERVTQR3EAVGQAEGQAEFGQAGRQACEQAERCQAEFGQAEFCGQAERGRQEAF","Francesco"),
    Message("Francesco","EFQxCAEFZVHTWGRSVTBW4TSEREY5HREY5HDSTRYEYDTBEYTGD3E5TRW54TRS3V5TERTW3REVTWGRHETGJHYJEBWSTRVZWESAEAECREWZS","Jessie"),
)
@Composable
fun Bottom(messages: List<Message>) {
    Row(){

    }
}
package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.letMeKnowColor


@Composable
fun RecentChat(myModelScreen: MyModelScreen) {
    val scrollState= rememberScrollState()
    Column(
        modifier = Modifier
            //.padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TopAppBar(
            {
                IconButton(onClick = {
                    ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)

                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = stringResource(R.string.user)
                    )
                }
                Text("Signup",color = Color.White,fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, top = 10.dp))
            })
       Conversations(MessageList)
    }
}
@Composable
fun Conversations(messages: List<Message>) {
    LazyColumn(modifier=Modifier.height(500.dp)) {
       items(items=messages){
           message->MessageBox(message)
       }
    }
}
data class Message(val sender:String,val reciver:String,val from:String)
val MessageList=listOf(
    Message("Jessie","Really exited","Francesco"),
    Message("Jessie","Really exitedQ4VAEWFGWSFGVGW4STRWHTRSGHWT4ASEFVTG5QAERVTQR3EAVGQAEGQAEFGQAGRQACEQAERCQAEFGQAEFCGQAERGRQEAF","Francesco"),
    Message("Jessie","Really exited","Francesco"),
    Message("Jessie","Really exited","Francesco"),
    Message("Jessie","Really exited  GRRAWdfsCQWAGRZFE CGWAESZFDVGWVERASFVQAEGGWSF","Francesco"),
    Message("Jessie","Really exitedQ4VAEWFGWSFGVGW4STRWHTRSGHWT4ASEFVTG5QAERVTQR3EAVGQAEGQAEFGQAGRQACEQAERCQAEFGQAEFCGQAERGRQEAF","Francesco")
)
@Composable
fun MessageBox(message:Message){
        var msgExpanded= rememberSaveable() {
            mutableStateOf(false)
        }
        Row{
            IconButton(onClick = {
                ScreenRouter.navigateTo(LetMeKnowScreen.Chat)

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
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier=Modifier.clickable{
                msgExpanded.value=!msgExpanded.value
            }){
                Text(message.sender,style=MaterialTheme.typography.subtitle2,  fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Spacer(modifier=Modifier.height(4.dp))
                Surface(shape=MaterialTheme.shapes.medium,elevation =1.dp){
                    Text(message.reciver,
                        style=MaterialTheme.typography.body1,
                        fontSize = 24.sp,
                        maxLines=if(msgExpanded.value)Int.MAX_VALUE else 1
                    )
                }

            }
        }

    }


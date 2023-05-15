package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.recived
import com.francesco.damata.letmeknowv1.ui.theme.sended

@Composable
fun Chat(myModelScreen: MyModelScreen) {
    val MessageListObs=rememberSaveable() {
        mutableStateOf(MessageListChat)
    }
    Column (modifier=Modifier
        .verticalScroll(rememberScrollState())
    ){

        TopAppBar(
            {
                IconButton({
                    ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        modifier=Modifier .padding(start = 5.dp)
                    )
                }
                IconButton({
                    ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)
                }) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }

                Text(
                    text = stringResource(R.string.user) +"#123456", color = Color.White, fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, top = 10.dp)
                )
            }
        )
        Conversation(MessageListObs.value)
        ChatBar(MessageListObs)
    }
}
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(
        modifier=Modifier.height(600.dp),
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
    ConstraintLayout() {
        val (row,Surface)=createRefs()
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
                        color = Color.White,
                        modifier = Modifier
                            .background(MaterialTheme.colors.sended)
                            .width(300.dp)


                    )
                }
            }
            else{

                Surface(shape=MaterialTheme.shapes.medium,elevation =1.dp){
                    Text(message.reciver,
                        style=MaterialTheme.typography.body1,
                        color = Color.White,
                        fontSize = 24.sp,
                        maxLines=if(msgExpanded.value)Int.MAX_VALUE else 5,
                        modifier= Modifier
                            .padding(start = 10.dp)
                            .background(MaterialTheme.colors.recived)
                            .widthIn(100.dp, 300.dp)
                    )
                }

            }

        }
    }

}
@Composable
fun ChatBar(MessageListObs: MutableState<MutableList<Message>>) {
    val inputMsg = rememberSaveable() {
        mutableStateOf("")
    }
    TextField(value = inputMsg.value,
        onValueChange = {
           inputMsg.value=it
        },

        textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),
        modifier=Modifier.fillMaxWidth(),
        trailingIcon={
            IconButton(onClick={
                var msg:Message
                msg=Message ("Francesco" , inputMsg.value,"Jessie")
                MessageListObs.value.add(msg)
            }){
                Icon(
                    imageVector = Icons.Default.Send,//https://fonts.google.com/icons
                    contentDescription =stringResource(R.string.sendMessage)//in there you can find into your messages
                )
            }
        })

}
val MessageListChat= mutableListOf<Message>(
    Message("Francesco","Really exited","Jessie"),
    Message("Jessie","Really exitedQ4VAEWFGWSFGVGW4STRWHTRSGHWT4ASEFVTG5QAERVTQR3EAVGQAEGQAEFGQAGRQACEQAERCQAEFGQAEFCGQAERGRQEAF","Francesco"),
    Message("Jessie","Really exited","Francesco"),
    Message("Francesco","Really exited","Jessie"),
    Message("Jessie","Really exited  GRRAWdfsCQWAGRZFE CGWAESZFDVGWVERASFVQAEGGWSF","Francesco"),
    Message("Jessie","Really exitedQ4VAEWFGWSFGVGW4STRWHTRSGHWT4ASEFVTG5QAERVTQR3EAVGQAEGQAEFGQAGRQACEQAERCQAEFGQAEFCGQAERGRQEAF","Francesco"),
    Message("Francesco","EFQxCAEFZVHTWGRSVTBW4TSEREY5HREY5HDSTRYEYDTBEYTGD3E5TRW54TRS3V5TERTW3REVTWGRHETGJHYJEBWSTRVZWESAEAECREWZS","Jessie"),
)

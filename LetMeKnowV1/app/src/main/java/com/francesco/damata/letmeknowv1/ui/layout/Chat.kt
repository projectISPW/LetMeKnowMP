package com.francesco.damata.letmeknowv1.ui.layout

import android.content.Context
import android.content.res.Configuration
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.db.Message
import com.francesco.damata.letmeknowv1.db.RepositoryMsg
import com.francesco.damata.letmeknowv1.db.UserDb
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.recived
import com.francesco.damata.letmeknowv1.ui.theme.sended
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


var messageList= listOf<com.francesco.damata.letmeknowv1.db.Message>()
fun getMsg(context: Context){
    val db=UserDb.getInstance(context)
    val repositoryMsg=RepositoryMsg(db.DaoMessage())
    var messages: List<com.francesco.damata.letmeknowv1.db.Message>
    CoroutineScope(Dispatchers.IO).launch {
        messages=repositoryMsg.readNext("0000000","0123456")
        if(messages!=null){
            messageList=messages
        }else{

        }
    }
}
@Composable
fun Chat(myModelScreen: MyModelScreen) {
    val context=LocalContext.current
    getMsg(context)
    val MessageListObs=rememberSaveable() {
        mutableStateOf(messageList)
    }
    Column(modifier=Modifier.fillMaxHeight()) {
            TopAppBar(
                {
                    IconButton({
                        ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button),
                            modifier = Modifier.padding(start = 5.dp)
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
                        text = stringResource(R.string.user) + "#123456",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 20.dp, top = 10.dp)
                    )
                }
            )
        Column(
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Conversation(MessageListObs.value)
        }
        ChatBar(context)
    }
}
@Composable
fun height(): Int {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp
}
@Composable
fun width(): Int {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp
}
@Composable
fun Conversation(messages: List<com.francesco.damata.letmeknowv1.db.Message>) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LazyColumn(
                modifier=Modifier.height(height().dp-100.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=messages){
                        message->
                    MessageChat(message,"Francesco","Jessie")
                }
            }
        }

        // Other wise
        else -> {
            LazyColumn(
                modifier=Modifier.height(height().dp-100.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=messages){
                        message->
                    MessageChat(message,"Francesco","Jessie")
                }
            }
        }
    }

}
@Composable
fun MessageChat(message:com.francesco.damata.letmeknowv1.db.Message, user:String, sender:String){
    var msgExpanded= rememberSaveable() {
        mutableStateOf(false)
    }
    if (message.sender==user) {
        Column(
        modifier=Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                modifier = Modifier
                    .layoutId("surface")
            ) {
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

    }
    else{
        Column(
            modifier=Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
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


@Composable
fun ChatBar(context:Context) {
    val db=UserDb.getInstance(context)
    val repositoryMsg=RepositoryMsg(db.DaoMessage())
    var messages: List<com.francesco.damata.letmeknowv1.db.Message>
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
                repositoryMsg.newMsg("0000000","0123456",inputMsg.value)

            }){
                Icon(
                    imageVector = Icons.Default.Send,//https://fonts.google.com/icons
                    contentDescription =stringResource(R.string.sendMessage)//in there you can find into your messages
                )
            }
        })

}


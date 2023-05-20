package com.francesco.damata.letmeknowv1.ui.layout

import android.app.Application
import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.db.Message
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.icon
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModel
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModelFactory


@Composable
fun RecentChat(myModelScreen: MyModelScreen) {
    myModelScreen.chatWith="0123456"
    val scrollState= rememberScrollState()
    val context= LocalContext.current
    val viewModel: MessageViewModel = viewModel(
        factory = MessageViewModelFactory(context.applicationContext as Application)
    )
    val items =viewModel.getChats("0000000").observeAsState(listOf()).value
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
        SearchBar()
        Conversations(getLastMessages(items,myModelScreen),myModelScreen.user)
        Button(LetMeKnowScreen.SearchUser,stringResource(R.string.contBtnSearch))
    }
}
@Composable
fun SearchBar(){
    val search = rememberSaveable() {
        mutableStateOf("")
    }
    TextField(value = search.value,
        onValueChange = {
           search.value=it
        },
        textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),
        modifier=Modifier.fillMaxWidth(),
        label = {
            Text(stringResource(R.string.searchMsg), fontSize = 24.sp)
        },
        trailingIcon={
            IconButton(onClick={}){
                Icon(
                    imageVector = Icons.Default.ManageSearch,//https://fonts.google.com/icons
                    contentDescription =stringResource(R.string.searchMsg)//in there you can find into your messages
                )
            }
        })
}
@Composable
fun Conversations(messages: List<Message>,curUsr:String) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LazyColumn(
                modifier=Modifier.height(height().dp-250.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=messages){
                        message->MessageBox(message,curUsr)
                }
            }
        }

        // Other wise
        else -> {
            LazyColumn(
                modifier=Modifier.height(height().dp-300.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=messages){
                        message->MessageBox(message,curUsr)
                }
            }
        }
    }
}
@Composable
fun MessageBox(message:Message,curUsr:String){
        var msgExpanded= rememberSaveable() {
            mutableStateOf(false)
        }
        var textUsr:String
        if(message.sender==curUsr){
            textUsr=message.reciver
        }else{
            textUsr=message.sender
        }

        Row{
            IconButton(onClick = {
                ScreenRouter.navigateTo(LetMeKnowScreen.Chat)
            }
            ) {
                Icon(Icons.Default.Message,
                    contentDescription = "content description",
                    tint = MaterialTheme.colors.icon,
                    modifier=Modifier
                        .size(70.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier=Modifier.clickable{
                msgExpanded.value=!msgExpanded.value
            }){
                Text(textUsr,style=MaterialTheme.typography.subtitle2,  fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Spacer(modifier=Modifier.height(4.dp))
                Surface(shape=MaterialTheme.shapes.medium,elevation =1.dp){
                    Text(message.text,
                        style=MaterialTheme.typography.body1,
                        fontSize = 24.sp,
                        maxLines=if(msgExpanded.value)Int.MAX_VALUE else 1
                    )
                }

            }
        }

    }


fun getLastMessages(messages: List<Message>,myModelScreen: MyModelScreen):List<Message>{
    var lastMessages: MutableList<Message> = mutableListOf()
        //MutableLiveData<MutableList<Message>>()//deve contenere l'ultimo messagio per ogni chat
    var listUsers:MutableList<String> =mutableListOf(myModelScreen.user)// deve cont
    if(messages!=null && messages!!.isNotEmpty()){

        for(i in messages!!){
            println("_____")
            println(i.text)
            if (!(listUsers.contains(i.sender))){
                //message that user has recived
                listUsers.add(i.sender)
                lastMessages.add(i)
            }
            else if (i.sender == myModelScreen.user && !(listUsers.contains(i.reciver))){
                //message that user has sended
                listUsers.add(i.reciver)
                lastMessages.add(i)
            }
            for(j in listUsers)println("utente .:"+j)
        }
    }
    return lastMessages
}


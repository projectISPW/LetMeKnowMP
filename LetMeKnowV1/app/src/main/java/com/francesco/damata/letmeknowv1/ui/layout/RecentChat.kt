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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.db.Message
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.button
import com.francesco.damata.letmeknowv1.ui.theme.icon
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModel
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModelFactory


@Composable
fun RecentChat(myModelScreen: MyModelScreen) {
    val context= LocalContext.current
    val viewModel: MessageViewModel = viewModel(
        factory = MessageViewModelFactory(context.applicationContext as Application)
    )
    val items =viewModel.getChats(myModelScreen.userClass.userid).observeAsState(listOf()).value
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
                    myModelScreen.onSearch=false
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = stringResource(R.string.user)
                    )
                }
                Text(stringResource(R.string.recentChat),color = Color.White,fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, top = 10.dp))
            })
        SearchBar(viewModel,items,myModelScreen)
        if(!myModelScreen.onSearch)Conversations(viewModel.getLastMessages(items,myModelScreen),myModelScreen.userClass.userid,myModelScreen)
        else Conversations(viewModel.SearchConversations(items,myModelScreen),myModelScreen.userClass.userid,myModelScreen)
        Button(onClick = {
            myModelScreen.onSearch=false
            ScreenRouter.navigateTo(LetMeKnowScreen.SearchUser)
        }, colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.button
        )){
            Text(stringResource(R.string.contBtnSearch),color = Color.White,fontSize = 20.sp)
        }
    }
}




@Composable
fun SearchBar(viewModel: MessageViewModel,messages:List<Message>,myModelScreen: MyModelScreen){
    TextField(value = myModelScreen.txtSrc,
        onValueChange = {
           myModelScreen.txtSrc=it
            if(myModelScreen.onSearch){
                viewModel.SearchConversations(messages,myModelScreen)
            }
        },
        textStyle = LocalTextStyle.current.copy(fontSize = 32.sp),
        modifier=Modifier
            .fillMaxWidth(),
        label = {
            Text(stringResource(R.string.searchMsg), fontSize = 24.sp)
        },
        trailingIcon={
            IconButton(onClick={
                myModelScreen.txtSrc=myModelScreen.txtSrc
                myModelScreen.onSearch=true
                }
            ){
                    if(myModelScreen.onSearch){
                        Icon(
                            imageVector = Icons.Default.SearchOff,//https://fonts.google.com/icons
                            contentDescription =  stringResource(R.string.close_search)
                        )
                    }else{
                        Icon(
                            imageVector = Icons.Default.ManageSearch,//https://fonts.google.com/icons
                            contentDescription =stringResource(R.string.new_search)
                        )
                    }

                }

        })
}
@Composable
fun Conversations(messages: List<Message>,curUsr:String,myModelScreen: MyModelScreen) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LazyColumn(
                modifier=Modifier.height(height().dp-250.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=messages){
                        message->MessageBox(message,curUsr,myModelScreen)
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
                        message->MessageBox(message,curUsr, myModelScreen = myModelScreen)
                }
            }
        }
    }
}
@Composable
fun MessageBox(message:Message,curUsr:String,myModelScreen: MyModelScreen){
        val msgExpanded= rememberSaveable {
            mutableStateOf(false)
        }
        var textUsr:String
        textUsr=message.sender
        if(message.sender==curUsr){
            textUsr=message.receiver
        }

        Row{
            IconButton(onClick = {
                myModelScreen.message=message
                myModelScreen.chatWith=textUsr
                ScreenRouter.navigateTo(LetMeKnowScreen.Chat)
            }
            ) {
                Icon(Icons.Default.Message,
                    contentDescription =stringResource(R.string.sendMessage),
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


package com.francesco.damata.letmeknowv1.ui.layout

import android.app.Application
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.db.Message
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.recived
import com.francesco.damata.letmeknowv1.ui.theme.sended
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModel
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModelFactory
import com.francesco.damata.letmeknowv1.viewModel.UserViewModel
import com.francesco.damata.letmeknowv1.viewModel.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Chat(myModelScreen: MyModelScreen) {
    val context=LocalContext.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel: MessageViewModel = viewModel(
        factory = MessageViewModelFactory(context.applicationContext as Application)
    )
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context.applicationContext as Application)
    )
    val items =viewModel.getChat(myModelScreen.userClass.userid,myModelScreen.chatWith).observeAsState(listOf()).value

    Column(modifier=Modifier.fillMaxHeight()) {
            TopAppBar(
                {
                    IconButton({
                        myModelScreen.onSearch=false
                        ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button),
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                    var userVisit = userViewModel.getUserVisited(myModelScreen.chatWith).observeAsState().value
                    IconButton({
                        if(userVisit!=null) {
                            myModelScreen.fromChat = true
                            myModelScreen.usrVisit = userVisit
                            ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }

                    Text(
                        text = stringResource(R.string.user) + myModelScreen.chatWith,
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
            Conversation(items,myModelScreen,listState)
        }
        viewModel.goLast(coroutineScope,listState, items, myModelScreen)
        ChatBar(viewModel,myModelScreen)
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
fun Conversation(messages: List<Message>,myModelScreen: MyModelScreen,listState: LazyListState) {
    val configuration = LocalConfiguration.current

// Remember a CoroutineScope to be able to launch
    for(i in messages){
        println("\n\n\n\n text message.:"+i.text+"\n\n\n\n")
    }
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LazyColumn(
                state=listState,
                modifier=Modifier.height(height().dp-100.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=messages){
                        message->
                    MessageChat(message,myModelScreen)
                }
            }
        }

        // Other wise
        else -> {
            LazyColumn(
                state=listState,
                modifier=Modifier.height(height().dp-130.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=messages){
                        message->
                    MessageChat(message,myModelScreen)
                }
                
            }
        }
    }

}
@Composable
fun MessageChat(message:Message,myModelScreen: MyModelScreen){
    if (message.sender==myModelScreen.userClass.userid) {
        Column(
        modifier=Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.End
    ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp
            ) {
                Text(
                    message.text,
                    style = MaterialTheme.typography.body1,
                    fontSize = 24.sp,
                    //maxLines = if (msgExpanded.value) Int.MAX_VALUE else 5,
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
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Surface(shape=MaterialTheme.shapes.medium,elevation =1.dp){
                Text(
                    message.text,
                    style=MaterialTheme.typography.body1,
                    color = Color.White,
                    fontSize = 24.sp,
                    //maxLines=if(msgExpanded.value)Int.MAX_VALUE else 5,
                    modifier= Modifier
                        .background(MaterialTheme.colors.recived)
                        .widthIn(100.dp, 300.dp)
                )
            }
        }
    }
}



@Composable
fun ChatBar(viewModel: MessageViewModel,myModelScreen:MyModelScreen) {
    var inputMsg = rememberSaveable() {
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
                viewModel.writeMessage(myModelScreen.userClass.userid,myModelScreen.chatWith,inputMsg.value)
                inputMsg.value=""
            }){
                Icon(
                    imageVector = Icons.Default.Send,//https://fonts.google.com/icons
                    contentDescription =stringResource(R.string.sendMessage)//in there you can find into your messages
                )
            }
        })

}


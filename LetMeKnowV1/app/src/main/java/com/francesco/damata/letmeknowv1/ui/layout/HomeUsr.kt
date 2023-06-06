package com.francesco.damata.letmeknowv1.ui.layout

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.db.User
import com.francesco.damata.letmeknowv1.notification.LetMeKnowFirebaseMessagingService
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.myBlue
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModel
import com.francesco.damata.letmeknowv1.viewModel.MessageViewModelFactory
import com.francesco.damata.letmeknowv1.viewModel.UserViewModel
import com.francesco.damata.letmeknowv1.viewModel.UserViewModelFactory
import com.google.firebase.messaging.FirebaseMessagingService

@Composable
fun HomeUsr(myModelScreen: MyModelScreen){
    val context = LocalContext.current
    val notificationService = LetMeKnowFirebaseMessagingService()
    val viewModel: MessageViewModel = viewModel(
        factory = MessageViewModelFactory(context.applicationContext as Application)
    )
    if(viewModel.getChats(myModelScreen.userClass.userid) != null){
        /*notificationService.showNotification("com.francesco.damata.letmeknowv1", 0,
            stringResource(R . string . app_name),
            "Have New Message "
        )*/
    }
    ProfColumn(myModelScreen)
}

@Composable
fun ProfColumn(myModelScreen: MyModelScreen) {
    if (myModelScreen.userClass.userid == "" && !myModelScreen.fromChat && !myModelScreen.onSearch) {
        val context = LocalContext.current
        val viewModel: UserViewModel = viewModel(
            factory = UserViewModelFactory(context.applicationContext as Application)
        )
        myModelScreen.userClass.userid =
            viewModel.getLogin(myModelScreen.userClass.email).observeAsState().value!!
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TopAppBar(
            {
                if ((!myModelScreen.onSearch && !myModelScreen.fromChat) || myModelScreen.usrVisit.userid==""){
                    IconButton(onClick = {
                        ScreenRouter.navigateTo(LetMeKnowScreen.Login)
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = stringResource(R.string.user) + myModelScreen.userClass.userid
                        )
                    }
                    Text(stringResource(R.string.topBarHome), color = Color.White, fontSize = 24.sp)
                }


                else if(myModelScreen.fromChat){
                    IconButton(onClick = {
                        ScreenRouter.navigateTo(LetMeKnowScreen.Chat)
                        myModelScreen.fromChat=false
                        myModelScreen.onSearch=false
                        myModelScreen.usrVisit= User("","","",1,1,1)
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.user) + myModelScreen.userClass.userid
                        )
                    }
                    Text(stringResource(R.string.visitUser), color = Color.White, fontSize = 24.sp)
                }


                else {
                    IconButton(onClick = {
                        ScreenRouter.navigateTo(LetMeKnowScreen.SearchResult)
                        myModelScreen.fromChat=false
                        myModelScreen.onSearch=false
                        myModelScreen.usrVisit= User("","","",1,1,1)
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.user) + myModelScreen.userClass.userid
                        )
                    }
                    Text(stringResource(R.string.visitUser), color = Color.White, fontSize = 24.sp)


                }

            })
        Text(text = stringResource(R.string.profile), color = Color.Black, fontSize = 40.sp)
        if (myModelScreen.onSearch || myModelScreen.fromChat) {
            Text(
                text = stringResource(R.string.user) + myModelScreen.usrVisit.userid,
                color = MaterialTheme.colors.myBlue,
                fontSize = 30.sp
            )
        } else {
            Text(
                text = stringResource(R.string.user) + myModelScreen.userClass.userid,
                color = MaterialTheme.colors.myBlue,
                fontSize = 30.sp
            )         //Al posto degli 00000 ci va $uid
        }
        InputTraits(true, myModelScreen)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!myModelScreen.onSearch && !myModelScreen.fromChat) {
                Button(
                    where = LetMeKnowScreen.EditProfile,
                    content = stringResource(R.string.edit_profile)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    where = LetMeKnowScreen.RecentChat,
                    content = stringResource(R.string.recentChat)
                )
            }
        }
        myImage(R.drawable.sleep_home)
    }
}









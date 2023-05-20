package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.button
import com.francesco.damata.letmeknowv1.ui.theme.myBlue

@Composable
fun HomeUsr(myModelScreen: MyModelScreen){
    profColumn(myModelScreen)
}

@Composable
fun profColumn(myModelScreen: MyModelScreen){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
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
                        contentDescription = stringResource(R.string.user)+myModelScreen.user
                    )
                }
                Text(stringResource(R.string.topBarHome),color = Color.White,fontSize = 24.sp)
            })
        Text(text = stringResource(R.string.profile),color = Color.Black,fontSize = 40.sp)
        Text(text = stringResource(R.string.user) +myModelScreen.user,color = MaterialTheme.colors.myBlue,fontSize = 30.sp)         //Al posto degli 00000 ci va $uid
        InputTraits(true,myModelScreen)
        Row(modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
            Button(where = LetMeKnowScreen.EditProfile, content =stringResource(R.string.edit_profile) )
            Spacer(modifier = Modifier.width(10.dp))
            Button(where = LetMeKnowScreen.RecentChat, content =stringResource(R.string.recentChat) )
        }
        myImage(R.drawable.sleep_home)
    }
}









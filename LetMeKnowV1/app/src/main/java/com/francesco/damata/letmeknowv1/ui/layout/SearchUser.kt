package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
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
import com.francesco.damata.letmeknowv1.ui.theme.myBlue

@Composable
fun SearchUser(myModelScreen: MyModelScreen) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TopAppBar(
            {
                IconButton(onClick = {
                    ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)         //Ricambia poi con il login
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                }
                IconButton(onClick = {
                    ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    stringResource(R.string.searchUserTopBar),
                    color = Color.White,
                    fontSize = 24.sp
                )
            })
        Text(text = "Search by profile parameters ", color =  MaterialTheme.colors.myBlue, fontSize = 25.sp)
    }
}
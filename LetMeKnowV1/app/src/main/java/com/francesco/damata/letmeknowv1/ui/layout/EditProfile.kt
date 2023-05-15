package com.francesco.damata.letmeknowv1.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun EditProfile(myModelScreen: MyModelScreen){      //Passo come parametro i tratti attuali del profilo
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
                    ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.backbutton)
                    )
                }
                Text(stringResource(R.string.edprof), color = Color.White, fontSize = 24.sp)
            })
        Text(text = stringResource(R.string.edit_profile),color = Color.Black,fontSize = 40.sp)
        Text(text = stringResource(R.string.selectTraits), color =  MaterialTheme.colors.myBlue, fontSize = 25.sp)
        InputTraits(false)
        Button(where = LetMeKnowScreen.HomeUsr , content =stringResource(R.string.confirm))
        myImage(R.drawable.edit_profile)
    }
}


package com.francesco.damata.letmeknowv1.ui.layout
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.db.User
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.icon
import com.francesco.damata.letmeknowv1.viewModel.UserViewModel
import com.francesco.damata.letmeknowv1.viewModel.UserViewModelFactory

@Composable
fun SearchResult(myModelScreen: MyModelScreen){
    val context= LocalContext.current
    val viewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context.applicationContext as Application)
    )
    Column(
        modifier = Modifier
            //.padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TopAppBar(
            {
                IconButton(onClick = {
                    ScreenRouter.navigateTo(LetMeKnowScreen.RecentChat)

                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.user)
                    )
                }
                Text(
                    stringResource(R.string.SearchResult), color = Color.White, fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, top = 10.dp)
                )
            })
        val searchResult = viewModel.getSearchResult(myModelScreen.onSearchUsr).observeAsState(listOf()).value
        FoundUser(users = searchResult,myModelScreen)

    }
}

@Composable
fun FoundUser(users: List<User>,myModelScreen: MyModelScreen) {
    LazyColumn(modifier = Modifier.height(height().dp)) {
        items(items = users) { user ->
            UserPar(user = user, myModelScreen )
        }
    }
}

@Composable
fun UserPar(user: User,myModelScreen: MyModelScreen) {
       val constraints = ConstraintSet {
        val userC = createRefFor("user")
        val textUser = createRefFor("textUser")
        val emot = createRefFor("emotional")
        val liv = createRefFor("lively")
        val opt = createRefFor("optimistic")
        val chat=createRefFor("chat")
        constrain(userC) {
            top.linkTo(parent.top,margin=10.dp)
            start.linkTo(parent.start)
        }
        constrain(textUser) {
            top.linkTo(parent.top)
            start.linkTo(userC.end,margin=15.dp)
        }

        constrain(emot) {
            top.linkTo(parent.top, margin = 25.dp)
            start.linkTo(userC.end, margin=25.dp)
        }
        constrain(liv) {
            top.linkTo(parent.top, margin = 25.dp)
            start.linkTo(emot.end, margin = 8.dp)
        }
        constrain(opt) {
            top.linkTo(parent.top, margin = 25.dp)
            start.linkTo(liv.end, margin = 8.dp)
        }
        constrain(chat) {
            top.linkTo(parent.top, margin = 10.dp)
            start.linkTo(opt.end,margin=25.dp)
        }
    }
    ConstraintLayout(constraints,
        modifier = Modifier.fillMaxSize()) {
        IconButton(
            modifier=Modifier .layoutId("user"),
            onClick = {
                myModelScreen.onSearch=true
                myModelScreen.usrVisit=user
                ScreenRouter.navigateTo(LetMeKnowScreen.HomeUsr)
            }) {
            Icon(
                Icons.Default.Person,
                contentDescription = "content description",
                tint = Color.White,
                modifier = Modifier
                    .size(75.dp)
                    //.then(Modifier.size(50.dp))
                    //.border(5.dp, MaterialTheme.colors.letMeKnowColor, shape = CircleShape)

                    .background(MaterialTheme.colors.icon, CircleShape)
            )
        }

        Text(
            user.userid,
            style = MaterialTheme.typography.body1,
            fontSize = 24.sp,
            modifier = Modifier.layoutId("textUser")
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.layoutId("emotional")
        ) {
            Text(
                "Emot",
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
            Text(
                user.emotional.toString(),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.layoutId("lively")
        ) {
            Text(
                "Liv",
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                user.lively.toString(),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.layoutId("optimistic")
        ) {
            Text(
                "Opt",
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                user.optimistic.toString(),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

        }
        IconButton(onClick = {
            myModelScreen.chatWith=user.userid
            ScreenRouter.navigateTo(LetMeKnowScreen.Chat)           //Chat con l'id del profilo cliccato

        },
            modifier = Modifier.layoutId("chat")) {
            Icon(
                Icons.Default.Message,
                contentDescription = "content description",
                tint = MaterialTheme.colors.icon,
                modifier = Modifier
                    .size(70.dp)
                    .border(0.dp, color = Color.White)
            )
        }
    }
}







































//@Composable
//fun UserPar(user: User) {
//    Row(
//        modifier=Modifier.fillMaxWidth()
//            .border(5.dp, Color.Red)
//
//
//
//    ){
//            Icon(
//                Icons.Default.Person,
//                contentDescription = "content description",
//                tint = "#8290f1".color,
//                modifier = Modifier
//                    .size(70.dp)
//                    .then(Modifier.size(50.dp))
//                    .border(5.dp, MaterialTheme.colors.letMeKnowColor, shape = CircleShape)
//            )
//        Spacer(modifier = Modifier.width(8.dp))
//        Column{
//            Text(user.uid,
//                style=MaterialTheme.typography.body1,
//                fontSize = 24.sp
//            )
//            ShowPar(user)
//            Spacer(modifier=Modifier.height(4.dp))
//        }
//    }
//}
//
//@Composable
//fun ShowPar(user : User){
//    Row(modifier = Modifier
//        .fillMaxWidth()
//        .padding(10.dp)){
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(
//                "Emo",
//                style = MaterialTheme.typography.subtitle2,
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp
//            )
//            Text(user.emotional.roundToInt().toString(),style=MaterialTheme.typography.subtitle2, fontWeight = FontWeight.Bold, fontSize = 24.sp)
//        }
//        Spacer(modifier = Modifier.width(40.dp))
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(
//                "Liv",
//                style = MaterialTheme.typography.subtitle2,
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp
//            )
//            Text(user.lively.roundToInt().toString(),style=MaterialTheme.typography.subtitle2, fontWeight = FontWeight.Bold, fontSize = 24.sp)
//        }
//        Spacer(modifier = Modifier.width(40.dp))
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(
//                "Opt",
//                style = MaterialTheme.typography.subtitle2,
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp
//            )
//            Text(user.optimism.roundToInt().toString(),style=MaterialTheme.typography.subtitle2, fontWeight = FontWeight.Bold, fontSize = 24.sp)
//        }
//        Spacer(modifier = Modifier.width(20.dp))
//        IconButton(onClick = {
//            ScreenRouter.navigateTo(LetMeKnowScreen.Chat)           //Chat con l'id del profilo cliccato
//        }) {
//            Icon(
//                Icons.Default.Message,
//                contentDescription = "content description",
//                tint = MaterialTheme.colors.icon,
//                modifier = Modifier
//                    .size(45.dp)
//                    .border(0.dp, color = Color.White)
//            )
//        }
//    }
//}
package com.francesco.damata.letmeknowv1.ui.layout
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.theme.color
import com.francesco.damata.letmeknowv1.ui.theme.icon
import com.francesco.damata.letmeknowv1.ui.theme.letMeKnowColor
import kotlin.math.roundToInt

@Composable
fun SearchResult(myModelScreen: MyModelScreen){
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
                    "Search Result", color = Color.White, fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp, top = 10.dp)
                )
            })
        FoundUser(users = UserList)
    }
}

@Composable
fun FoundUser(users: List<User>) {
    LazyColumn(modifier = Modifier.height(500.dp)) {
        items(items = users) { user ->
            UserPar(user = user)
        }
    }
}

@Composable
fun UserPar(user: User) {
    val constraints = ConstraintSet {
        val user = createRefFor("user")
        val textUser = createRefFor("textUser")
        val emot = createRefFor("emotional")
        val liv = createRefFor("lively")
        val opt = createRefFor("optimistic")
        val chat=createRefFor("chat")
        constrain(user) {
            top.linkTo(parent.top,margin=10.dp)
            start.linkTo(parent.start)
        }
        constrain(textUser) {
            top.linkTo(parent.top)
            start.linkTo(user.end,margin=15.dp)
        }

        constrain(emot) {
            top.linkTo(parent.top, margin = 25.dp)
            start.linkTo(user.end, margin=25.dp)
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
        Icon(
            Icons.Default.Person,
            contentDescription = "content description",
            tint = Color.White,
            modifier = Modifier
                .size(75.dp)
                //.then(Modifier.size(50.dp))
                //.border(5.dp, MaterialTheme.colors.letMeKnowColor, shape = CircleShape)
                .layoutId("user")
                .background(MaterialTheme.colors.icon, CircleShape)
        )
        Text(
            user.uid,
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
                user.emotional.roundToInt().toString(),
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
                user.lively.roundToInt().toString(),
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
                user.optimism.roundToInt().toString(),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

        }
        IconButton(onClick = {
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
data class User(val uid:String,val emotional: Float,val lively: Float,val optimism: Float)
val UserList=listOf(
    User("#000000",1f,1f,1f),
    User("#000001",3f,2f,4f),
)








































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
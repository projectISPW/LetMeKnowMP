import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import com.francesco.damata.letmeknowv1.ui.layout.ShowPar
import com.francesco.damata.letmeknowv1.ui.layout.User
import com.francesco.damata.letmeknowv1.ui.theme.color
import com.francesco.damata.letmeknowv1.ui.theme.letMeKnowColor
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import com.francesco.damata.letmeknowv1.ui.layout.LetMeKnowScreen
import com.francesco.damata.letmeknowv1.ui.layout.ScreenRouter
import com.francesco.damata.letmeknowv1.ui.theme.icon
import kotlin.math.roundToInt

@Composable
fun UserParV1(user: User) {
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
                .background(MaterialTheme.colors.icon,CircleShape)
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

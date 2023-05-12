package com.francesco.damata.letmeknowv1.ui.layout

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.francesco.damata.letmeknowv1.R
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
enum class LetMeKnowScreen(@StringRes val title: Int) {
    Login(title= R.string.login),
    HomeUsr(title=R.string.home),// insert 1,
    Signup(title=R.string.signup),
    RecentChat(title=R.string.recentChat),
    Chat(title=R.string.chat),
    SearchUser(title=R.string.searchusr),
    EditProfile(title = R.string.editprof)
    //TODO //







}
object ScreenRouter {
        var currentScreen: MutableState<LetMeKnowScreen> = mutableStateOf(LetMeKnowScreen.RecentChat)

    fun navigateTo(destination: LetMeKnowScreen) {
        currentScreen.value = destination
    }
}

@Composable
fun TheLayout() {
    val myModel = rememberSaveable {
        MyModelScreen()
    }

    when(ScreenRouter.currentScreen.value) {
        LetMeKnowScreen.Login->MainLayout(myModel)
        LetMeKnowScreen.HomeUsr->HomeUsr(myModel)// insert 2
        LetMeKnowScreen.Signup->Signup(myModel)

        LetMeKnowScreen.RecentChat-> RecentChat(myModel)
        LetMeKnowScreen.Chat->Chat(myModel)

        LetMeKnowScreen.SearchUser->SearchUser(myModel)
        LetMeKnowScreen.EditProfile->EditProfile(myModel)
        //TODO







    }
}
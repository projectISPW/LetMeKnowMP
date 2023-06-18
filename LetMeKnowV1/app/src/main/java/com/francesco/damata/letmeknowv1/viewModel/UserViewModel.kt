package com.francesco.damata.letmeknowv1.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.francesco.damata.letmeknowv1.db.LetMeKnowDB
import com.francesco.damata.letmeknowv1.db.RepositoryUsr
import com.francesco.damata.letmeknowv1.db.User
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import com.francesco.damata.letmeknowv1.ui.layout.Exceptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


class UserViewModel (application: Application) : AndroidViewModel(application){
    private var repository: RepositoryUsr
    init{
        val usrDao= LetMeKnowDB.getInstance(application).DaoUser()
        repository= RepositoryUsr(usrDao)
    }
  fun getLogin(id:String, pswd:String): LiveData<User> {
        return repository.getLogin(id,pswd)
    }
    fun getLogin(email:String):LiveData<String>{
        return repository.getLogin(email)
    }
    fun getUserVisited(uid : String) : LiveData<User>{
        return repository.getVisitUser(uid )
    }
    fun getSearchResult(usr:User): LiveData<MutableList<User>>{
        return repository.searchUsr(usr)
    }
    fun update(usr:User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUsr(usr)
        }
    }
    @Suppress("unused")
    fun validateMail(context: Context,  myModelScreen: MyModelScreen, user: User){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val emailAddr = InternetAddress(user.email)
                emailAddr.validate()
                println("\n\n\n"+repository.getLoginViewModel(user.email)+ "\n\n\n")
                if(repository.getLoginViewModel(user.email)==null){
                    myModelScreen.userClass=user
                    newUser(user)
                    myModelScreen.emailValidator=true
                }else{
                    myModelScreen.emailValidator=false
                    CoroutineScope(Dispatchers.Main).launch {
                        Exceptions.mailUsed(context)
                    }
                }
            } catch (e:AddressException ) {
                myModelScreen.emailValidator=false
                CoroutineScope(Dispatchers.Main).launch {
                    Exceptions.mailIncorrect(context)
                }
            }
        }

    }
    @Suppress("unused")
    fun newUser(usr:User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.newUsr(usr)
        }
    }
}
@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(application) as T
    }
}
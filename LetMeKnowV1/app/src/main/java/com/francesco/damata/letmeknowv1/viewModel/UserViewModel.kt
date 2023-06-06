package com.francesco.damata.letmeknowv1.viewModel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.francesco.damata.letmeknowv1.db.LetMeKnowDB
import com.francesco.damata.letmeknowv1.db.RepositoryUsr
import com.francesco.damata.letmeknowv1.db.User
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
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
    fun validateMail(str:String,myModelScreen: MyModelScreen){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val emailAddr = InternetAddress(str)
                emailAddr.validate()
                println("\n\n\n"+repository.getLoginViewModel(str)+ "\n\n\n")
                if(repository.getLoginViewModel(str)==null){
                    myModelScreen.emailValidator=true
                    println("\n\n\n check mail avvenuto correttamente \n\n\n")
                }else{
                    myModelScreen.emailValidator=false
                }
            } catch (e:AddressException ) {
                println("\n\n\n check mail avvenuto non correttamente \n\n\n")
                myModelScreen.emailValidator=false
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
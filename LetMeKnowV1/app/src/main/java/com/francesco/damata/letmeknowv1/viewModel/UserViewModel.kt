package com.francesco.damata.letmeknowv1.viewModel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.*
import com.francesco.damata.letmeknowv1.db.LetMeKnowDB
import com.francesco.damata.letmeknowv1.db.RepositoryUsr
import com.francesco.damata.letmeknowv1.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.validator.routines.EmailValidator
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
    fun newUser(usr:User){
        var bool:Boolean
        viewModelScope.launch(Dispatchers.IO) {
               bool=EmailValidator.getInstance().isValid(usr.email)
               if(bool) {
                   repository.newUsr(usr)
                   println("la tua mail è inserita correttamente ")

               }else{
                   println("error on the mail occurred ")
               }
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
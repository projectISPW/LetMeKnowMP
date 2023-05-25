package com.francesco.damata.letmeknowv1.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.francesco.damata.letmeknowv1.db.LetMeKnowDB
import com.francesco.damata.letmeknowv1.db.RepositoryUsr
import com.francesco.damata.letmeknowv1.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    fun getSearchResult(uid : String,emo : Int,lv : Int,opt : Int): LiveData<MutableList<User>>{
        return repository.searchUsr(uid,emo,lv,opt)
    }
    fun update(usr:User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUsr(usr)
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
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
    fun update(usr:User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(usr)
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
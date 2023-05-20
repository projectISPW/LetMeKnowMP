package com.francesco.damata.letmeknowv1.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.francesco.damata.letmeknowv1.db.Message
import com.francesco.damata.letmeknowv1.db.RepositoryMsg
import com.francesco.damata.letmeknowv1.db.UserDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
class MessageViewModel (application:Application) : AndroidViewModel(application){
    private var repository: RepositoryMsg

    init{
        val messageDao=UserDb.getInstance(application).DaoMessage()
        repository=RepositoryMsg(messageDao)
    }
    fun getChat(sender:String,reciver:String): LiveData<MutableList<Message>> {
        return repository.getChat(sender,reciver)
    }
    fun getChats(user:String): LiveData<MutableList<Message>>{
        return repository.getChats(user)
    }


    fun writeMessage(sender:String, reciver:String, text:String){
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val current = LocalDateTime.now().format(formatter)
        viewModelScope.launch(Dispatchers.IO) {
            repository.writeMsg(Message(current,sender,reciver,text))
        }
    }

}
@Suppress("UNCHECKED_CAST")
class MessageViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MessageViewModel(application) as T
    }
}
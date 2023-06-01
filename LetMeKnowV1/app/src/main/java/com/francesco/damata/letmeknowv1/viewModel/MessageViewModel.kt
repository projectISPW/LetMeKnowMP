package com.francesco.damata.letmeknowv1.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.francesco.damata.letmeknowv1.db.Message
import com.francesco.damata.letmeknowv1.db.RepositoryMsg
import com.francesco.damata.letmeknowv1.db.LetMeKnowDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




import android.os.Build
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import com.francesco.damata.letmeknowv1.screen.MyModelScreen
import kotlinx.coroutines.CoroutineScope

class MessageViewModel (application:Application) : AndroidViewModel(application){
    private var repository: RepositoryMsg
    init{
        val messageDao=LetMeKnowDB.getInstance(application).DaoMessage()
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

    fun goLast(coroutineScope: CoroutineScope, listState: LazyListState, messages: List<Message>, model: MyModelScreen){
        var msg:Message?=null
        var i : Message
        var count=0
        if(messages.isNotEmpty()){
            i= messages.get(count)
            while(count<messages.size-1 && msg==null ){
                if(i.text==model.message.text && i.dateTime==model.message.dateTime){
                    msg=i
                }
                count++
                i= messages.get(count)
            }
        }
        if(msg==null && messages.isNotEmpty())msg=messages.last()
        coroutineScope.launch {
            // Animate scroll to the 10th item
            if(msg!=null){
                listState.animateScrollToItem(messages.indexOf(msg))
            }
        }
    }
    fun getLastMessages(messages: List<Message>,myModelScreen: MyModelScreen):List<Message>{
        var lastMessages: MutableList<Message> = mutableListOf()  //deve contenere l'ultimo messagio per ogni chat
        var listUsers:MutableList<String> =mutableListOf(myModelScreen.userClass.userid)// deve cont
        if(messages!=null && messages!!.isNotEmpty()){
            for(i in messages!!){
                if (!(listUsers.contains(i.sender))){
                    //message that user has recived
                    listUsers.add(i.sender)
                    lastMessages.add(i)
                }
                else if (i.sender == myModelScreen.userClass.userid && !(listUsers.contains(i.reciver))){
                    //message that user has sended
                    listUsers.add(i.reciver)
                    lastMessages.add(i)
                }
            }
        }
        return lastMessages
    }
    fun SearchConversations(messages:List<Message>,myModelScreen: MyModelScreen):List<Message>{
        var searchMessages: MutableList<Message> = mutableListOf()  //deve contenere l'ultimo messagio per ogni chat
        if(messages!=null && messages!!.isNotEmpty()){
            for(i in messages!!){
                if(i.text.contains(myModelScreen.txtSrc))searchMessages.add(i)
            }
        }
        return searchMessages
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
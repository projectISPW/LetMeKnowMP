package com.francesco.damata.letmeknowv1.db

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RepositoryMsg(private val msgDao:DaoMessage) {
    fun readNext(userid:String){

    }
    fun readNext(sender:String,reciver:String):List<Message>{
        return msgDao.getChat(sender,reciver)
    }
    fun update(msg:Message){
        CoroutineScope(Dispatchers.IO).launch{
            msgDao.update(msg)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun newMsg(sender:String, reciver:String, text:String){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)
        println("i am here")
        CoroutineScope(Dispatchers.IO).launch{
            msgDao.insert(Message(current,sender,reciver,text))
        }

    }
}
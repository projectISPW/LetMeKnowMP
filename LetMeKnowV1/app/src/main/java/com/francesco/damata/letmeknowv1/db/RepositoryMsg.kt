package com.francesco.damata.letmeknowv1.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RepositoryMsg(private val msgDao:DaoMessage) {
    fun readNext(userid:String){

    }
    fun getChat(sender:String,reciver:String): LiveData<MutableList<Message>> {
        return msgDao.getChat(sender,reciver)
    }
    fun update(msg:Message){
        CoroutineScope(Dispatchers.IO).launch{
            msgDao.update(msg)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun writeMsg(message:Message){
        msgDao.insert(message)
    }
}
package com.francesco.damata.letmeknowv1.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData


class RepositoryMsg(private val msgDao:DaoMessage) {
    fun getChat(sender:String, receiver:String): LiveData<MutableList<Message>> {
        return msgDao.getChat(sender,receiver)
    }
    fun getChats(user:String): LiveData<MutableList<Message>>{
        return msgDao.getChats(user)
    }
   

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun writeMsg(message:Message){
        msgDao.insert(message)
    }
}
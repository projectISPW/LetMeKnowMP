package com.francesco.damata.letmeknowv1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoMessage {
    @Insert
    suspend fun insert(message: Message)
    @Insert
    suspend fun update(message: Message)
    //@Query("INSERT INTO Message (dateTime,sender,reciver,text) VALUES (datetime('now'), :sender , :reciver , :text ")
    //fun insertMessage(sender:String,reciver:String,text:String)

    @Query("SELECT * FROM Message WHERE sender= :usr OR reciver= :usr order by dateTime DESC")
    fun getChats(usr:String):LiveData<MutableList<Message>>


    @Query("SELECT * FROM Message WHERE (Sender= :sender AND Reciver= :reciver) OR (Sender= :reciver AND Reciver= :sender) order by dateTime")
    fun getChat(reciver:String,sender:String):  LiveData<MutableList<Message>>


}
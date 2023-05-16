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

    @Query("SELECT * FROM Message WHERE sender= :usr OR reciver= :usr")
    fun getChats(usr:String): List<Message>

    @Query("SELECT * FROM Message WHERE (Sender= :sender AND Reciver= :reciver) OR (Sender= :reciver AND Reciver= :sender) ")
    fun getChat(reciver:String,sender:String): List<Message>

}
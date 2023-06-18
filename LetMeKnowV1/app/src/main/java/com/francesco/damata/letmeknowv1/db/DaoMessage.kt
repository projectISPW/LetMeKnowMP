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
    //@Query("INSERT INTO Message (dateTime,sender,receiver,text) VALUES (datetime('now'), :sender , :receiver , :text ")
    //fun insertMessage(sender:String,receiver:String,text:String)

    @Query("SELECT * FROM Message WHERE sender= :usr OR receiver= :usr order by dateTime DESC")
    fun getChats(usr:String):LiveData<MutableList<Message>>


    @Query("SELECT * FROM Message WHERE (Sender= :sender AND Receiver= :receiver) OR (Sender= :receiver AND Receiver= :sender) order by dateTime")
    fun getChat(receiver:String,sender:String):  LiveData<MutableList<Message>>


}
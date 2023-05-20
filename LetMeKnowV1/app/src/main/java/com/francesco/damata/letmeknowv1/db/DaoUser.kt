package com.francesco.damata.letmeknowv1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoUser{
    @Insert
    suspend fun insert(user: User)
    @Insert
    suspend fun update(user: User)

    @Query("SELECT * FROM User WHERE (userid= :id AND password= :password) OR (email= :id AND password= :password)")
    fun getLogin(id:String,password:String):LiveData<User>
    @Query("SELECT * FROM User WHERE (userid= '0000000' AND password= 'password') OR (email= 'francescodamata@gmail.com' AND password= 'password')")
    fun getLogin():LiveData<User>







}
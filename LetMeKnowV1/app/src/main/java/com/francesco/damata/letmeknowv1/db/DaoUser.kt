package com.francesco.damata.letmeknowv1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoUser{
    @Insert
    suspend fun insert(user: User)
    @Query("SELECT userid FROM User")
    fun getUids():List<String>?
    @Query("SELECT * FROM User WHERE (userid= :id AND password= :password) OR (email= :id AND password= :password)")
    fun getLogin(id:String,password:String):LiveData<User>
    @Query("SELECT * FROM User WHERE (userid= '0000000' AND password= 'password') OR (email= 'francescodamata@gmail.com' AND password= 'password')")
    fun getLogin():LiveData<User>

    @Query ("Select * from User where (:emotional<=5 AND :lively<=5 AND :optimistic<=5 AND userid!=:usr)")
    fun getSearch(usr:String,emotional:Int,lively:Int,optimistic:Int):LiveData<MutableList<User>>
    @Query("UPDATE   User SET emotional = :emotional,lively = :lively,optimistic = :opt WHERE userid=:usr")
    fun update(usr:String,emotional:Int,lively:Int,opt:Int)






}
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

    @Query("SELECT userid FROM User where email= :email")
    fun getUid(email:String):LiveData<String>

    @Query("SELECT email FROM User")
    fun getEmails():List<String>?
    @Query("SELECT * FROM User WHERE (userid= :id AND password= :password) OR (email= :id AND password= :password)")
    fun getLogin(id:String,password:String):LiveData<User>

    @Query ("Select * from User where (emotional<=:emotional AND lively<=:lively AND optimistic<=:optimistic AND userid!=:usr)")
    fun getSearch(usr:String,emotional:Int,lively:Int,optimistic:Int):LiveData<MutableList<User>>

    @Query ("SELECT * FROM User WHERE userid = :uid")
    fun getVisitUser(uid : String): LiveData<User>




    @Query("UPDATE   User SET emotional = :emotional,lively = :lively,optimistic = :opt WHERE userid=:usr")
    fun update(usr:String,emotional:Int,lively:Int,opt:Int)






}
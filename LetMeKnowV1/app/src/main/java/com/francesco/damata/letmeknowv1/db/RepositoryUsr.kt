package com.francesco.damata.letmeknowv1.db

import androidx.lifecycle.LiveData

class RepositoryUsr(private val usrDao:DaoUser ){
    fun getLogin(id:String, pswd:String):LiveData<User>{
        return usrDao.getLogin(id,pswd)
    }
     fun updateUsr(user: User) {
        usrDao.update1(user.userid,user.emotional,user.lively,user.optimistic)
    }
    suspend fun newUsr(user:User){
        //assign the id to the usr



        usrDao.insert(user)



    }

    fun searchUsr(uid : String,emo : Int, lv: Int,opt : Int): LiveData<MutableList<User>>{
        return usrDao.getSearch(uid,emo,lv,opt)
    }
}
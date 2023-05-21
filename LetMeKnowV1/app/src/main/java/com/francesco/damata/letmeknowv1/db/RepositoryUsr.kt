package com.francesco.damata.letmeknowv1.db

import androidx.lifecycle.LiveData

class RepositoryUsr(private val usrDao:DaoUser ){
    fun getLogin(id:String, pswd:String):LiveData<User>{
        return usrDao.getLogin(id,pswd)
    }
    suspend fun update(user: User) {
        usrDao.update(user)
    }

}
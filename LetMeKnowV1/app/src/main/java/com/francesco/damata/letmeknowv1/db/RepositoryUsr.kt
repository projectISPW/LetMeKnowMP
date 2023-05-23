package com.francesco.damata.letmeknowv1.db

import androidx.lifecycle.LiveData

class RepositoryUsr(private val usrDao:DaoUser ){
    fun getLogin(id:String, pswd:String):LiveData<User>{
        return usrDao.getLogin(id,pswd)
    }
     fun update1(user: User) {
        println("\n\n\n"+user.userid+"parametri ("+user.emotional+user.lively+user.optimistic+"\n\n\n")
        usrDao.update1(user.userid,user.emotional,user.lively,user.optimistic)
    }

}
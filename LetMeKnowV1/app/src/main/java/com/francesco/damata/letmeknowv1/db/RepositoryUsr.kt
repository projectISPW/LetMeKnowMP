package com.francesco.damata.letmeknowv1.db

import androidx.lifecycle.LiveData
import java.util.*

class RepositoryUsr(private val usrDao:DaoUser ){
    fun getLogin(id:String, passwd:String):LiveData<User>{
        return usrDao.getLogin(id,passwd)
    }
    fun getVisitUser(uid : String) : LiveData<User>{
        return usrDao.getVisitUser(uid)
    }
    fun getLogin(email:String):LiveData<String>{
        return usrDao.getUid(email)
    }
    fun getLoginViewModel(email:String):String{
        return usrDao.getUidViewmodel(email)
    }
     fun updateUsr(user: User) {
        usrDao.update(user.userid,user.emotional,user.lively,user.optimistic)
    }
    suspend fun newUsr(user:User) {
        //assign the id to the usr
        var random : Int
        val min = 1000000
        val max = 9999999
        var check: String? = null
        var equal = true
        val emails:  List<String>  = usrDao.getEmails() ?: listOf()

        if(!emails.contains(user.email) ){
            val uidList: List<String> = usrDao.getUids() ?: listOf("0000000")
            val randomGenerator = Random()
            while (equal) {
                random = randomGenerator.nextInt(max)
                equal = false
                if (random < min - 1) equal = true
                check = "" + random
                if (!equal) for (uid in uidList) {
                    if (check == uid) {
                        equal = true
                    }
                }
            }
            if (check != null) {
                user.userid=check
            }
            usrDao.insert(user)
        }

    }
    fun searchUsr(usr:User): LiveData<MutableList<User>>{
        return usrDao.getSearch(usr.userid,usr.emotional,usr.lively,usr.optimistic)
    }


}
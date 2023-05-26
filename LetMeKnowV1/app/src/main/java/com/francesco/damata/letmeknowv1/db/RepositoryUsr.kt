package com.francesco.damata.letmeknowv1.db

import androidx.lifecycle.LiveData
import java.util.*

class RepositoryUsr(private val usrDao:DaoUser ){
    fun getLogin(id:String, pswd:String):LiveData<User>{
        return usrDao.getLogin(id,pswd)
    }
    fun getVisitUser(uid : String) : LiveData<User>{
        return usrDao.getVisitUser(uid)
    }
    fun getLogin(email:String):LiveData<String>{
        return usrDao.getUid(email)
    }
     fun updateUsr(user: User) {
        usrDao.update(user.userid,user.emotional,user.lively,user.optimistic)
    }
    suspend fun newUsr(user:User) {
        //assign the id to the usr
        var random = 0
        val min = 1000000
        val max = 9999999
        var check: String? = null
        var equal = true
        val emails:  List<String>  = usrDao.getEmails() ?: listOf<String>()

        if(!emails.contains(user.email) ){
            val uidList: List<String> = usrDao.getUids() ?: listOf<String>("0000000")
            var randomno = Random()
            while (equal) {
                random = randomno.nextInt(max)
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
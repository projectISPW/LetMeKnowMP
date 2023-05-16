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




}
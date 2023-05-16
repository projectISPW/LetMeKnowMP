package com.francesco.damata.letmeknowv1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey(autoGenerate = true) var userid: Int,
                   var password: String,
                   var email: String,
                   var emotional: Int,
                   var lively: Int,
                   var optimistic: Int
)
package com.francesco.damata.letmeknowv1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(   var userid: String,
                   var password: String,
                   @PrimaryKey var email: String,
                   var emotional: Int,
                   var lively: Int,
                   var optimistic: Int
)

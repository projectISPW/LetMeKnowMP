package com.francesco.damata.letmeknowv1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(
    entities = [User::class,Message::class],
    version = 1
)
abstract class UserDb : RoomDatabase() {
    abstract fun DaoUser():DaoUser
    abstract fun DaoMessage():DaoMessage
    companion object {
        private var db: UserDb? = null
        fun getInstance(context: Context): UserDb {
            if(db==null) {
                db = databaseBuilder(
                    context,
                    UserDb::class.java, "letMeKnowdbv5.db"
                ).createFromAsset("letMeKnowdbv5.db")
                    .build()
            }

            return db as UserDb //Oggetto statico tornato dalla singleton
        }
    }
}

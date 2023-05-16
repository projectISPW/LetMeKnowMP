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
    companion object {
        private var db: UserDb? = null


        fun getInstance(context: Context): UserDb {
            if (db == null) {
                db = databaseBuilder(
                    context,
                    UserDb::class.java, "letMeKnowdbv1.db"
                ).createFromAsset("letMeKnowdbv1.db")
                    .build()
            }
            return db as UserDb
        }
    }
}

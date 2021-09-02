package com.nan.xarch.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nan.xarch.XArchApplication
import com.nan.xarch.bean.User
import com.nan.xarch.database.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class XDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private val db: XDatabase by lazy {
            Room.databaseBuilder(
                XArchApplication.instance,
                XDatabase::class.java, "database-name"
            ).build()
        }

        fun userDao(): UserDao {
            return db.userDao()
        }
    }

}
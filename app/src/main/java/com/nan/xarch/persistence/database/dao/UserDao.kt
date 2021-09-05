package com.nan.xarch.persistence.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nan.xarch.bean.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    suspend fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

}
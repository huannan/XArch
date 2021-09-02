package com.nan.xarch.bean

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

// 多个主键
// @Entity(tableName = "user", primaryKeys = ["first_name", "last_name"])
// 定义表名称，SQLite 中的表名称不区分大小写
@Entity(tableName = "user")
data class User(
    // 主键分配自动ID
    // @PrimaryKey(autoGenerate = true) val uid: Int,
    @PrimaryKey var uid: Int,
    // 如果您希望列具有不同的名称，请将 @ColumnInfo 注释添加到字段
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    // 如果某个实体中有您不想保留的字段，则可以使用 @Ignore 为这些字段添加注释
    @Ignore val picture: Bitmap?
) {
    constructor() : this(0, "", "", null) {

    }
}
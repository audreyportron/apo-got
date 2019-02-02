package com.apo.mobgengot.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoriesDAO {

    @Insert
    fun insertAll(categories: List<CategoryEntity>)

    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryEntity>?

    @Delete
    fun deleteAll(categories: List<CategoryEntity>)


}
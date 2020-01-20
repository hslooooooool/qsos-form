package vip.qsos.form_lib.model

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * @author : 华清松
 */
interface AbsFormDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(table: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tables: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(table: T)

    @Update
    fun update(tables: List<T>)

    @Delete
    fun delete(table: T)

    @Delete
    fun delete(tables: List<T>)

}
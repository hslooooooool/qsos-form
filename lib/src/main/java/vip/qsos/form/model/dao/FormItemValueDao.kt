package vip.qsos.form.model.dao

import androidx.room.*
import qsos.core.form.db.entity.Value

/**
 * @author : 华清松
 * 表单值列表项 Dao 层
 */
@Dao
interface FormItemValueDao {

    @Query("SELECT * FROM formItemValue where formItemId=:formItemId ORDER BY position")
    fun getByFormItemId(formItemId: Long): List<Value>

    @Query("SELECT * FROM formItemValue where formItemId=:formItemId AND userName like :key OR userDesc like :key")
    fun getUserByFormItemIdAndUserDesc(formItemId: Long, key: String?): List<Value>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: Value): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(value: Value)

    @Update
    fun update(value: List<Value>)

    @Delete
    fun delete(value: Value)

    @Query("DELETE FROM formItemValue WHERE formItemId=:formItemId")
    fun deleteByFormItemId(formItemId: Long)

    @Query("DELETE FROM formItemValue WHERE formItemId=:formItemId AND userDesc=:userDesc")
    fun deleteByFormItemIdAndUserDesc(formItemId: Long?, userDesc: String)

    @Query("DELETE FROM formItemValue where formItemId=:formItemId")
    fun deleteByFormItemId(formItemId: Long?)

}
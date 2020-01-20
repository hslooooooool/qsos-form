package vip.qsos.form_lib.model

import androidx.room.Dao
import androidx.room.Query

/**
 * @author : 华清松
 * 表单值列表项 Dao 层
 */
@Dao
interface FormValueDao : AbsFormDao<FormValueEntity> {

    @Query("SELECT * FROM formItemValue where formItemId=:formItemId ORDER BY position")
    fun getByFormItemId(formItemId: Long): List<FormValueEntity>

    @Query("DELETE FROM formItemValue WHERE formItemId=:formItemId")
    fun deleteByFormItemId(formItemId: Long)

    @Query("DELETE FROM formItemValue where formItemId=:formItemId")
    fun deleteByFormItemId(formItemId: Long?)

}
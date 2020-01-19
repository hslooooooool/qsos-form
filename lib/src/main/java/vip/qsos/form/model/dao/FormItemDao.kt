package vip.qsos.form.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import vip.qsos.form.model.entity.FormItem

/**
 * @author : 华清松
 * 表单项 Dao 层
 */
@Dao
interface FormItemDao {

    /**
     * 获取表单项下所以值列表
     * @param formId 表单项ID
     */
    @Query("SELECT * FROM formItem where formId=:formId")
    fun getFormItemByFormId(formId: Long): List<FormItem>

    /**
     * 获取表单项
     * @param id 表单项ID
     */
    @Query("SELECT * FROM formItem where id=:id")
    fun getFormItem(id: Long): FormItem?

    /**
     * 插入表单项
     * @param item 表单项
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: FormItem): Long

}
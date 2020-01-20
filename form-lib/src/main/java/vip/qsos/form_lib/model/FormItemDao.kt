package vip.qsos.form_lib.model

import androidx.room.Dao
import androidx.room.Query

/**
 * @author : 华清松
 * 表单项 Dao 层
 */
@Dao
interface FormItemDao : AbsFormDao<FormItemEntity> {

    /**
     * 获取表单项下所以值列表
     * @param formId 表单项ID
     */
    @Query("SELECT * FROM formItem where formId=:formId")
    fun getFormItemByFormId(formId: Long): List<FormItemEntity>

    /**
     * 获取表单项
     * @param id 表单项ID
     */
    @Query("SELECT * FROM formItem where id=:id")
    fun getFormItem(id: Long): FormItemEntity?

}
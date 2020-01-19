package vip.qsos.form.model.dao

import androidx.room.*
import vip.qsos.form.model.entity.FormEntity

/**
 * @author : 华清松
 * 表单 Dao 层
 *
 * 注意：最新版本的Room支持更多RxJava相关操作，如删除是否完成观察者
 */
@Dao
interface FormDao {

    @Query("SELECT * FROM form where id=:id")
    fun getFormById(id: Long): FormEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(form: FormEntity): Long

    @Delete
    fun delete(form: FormEntity)

}
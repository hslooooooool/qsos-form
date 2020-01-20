package vip.qsos.form_lib.model

import androidx.room.Dao
import androidx.room.Query

/**
 * @author : 华清松
 * 表单 Dao 层
 *
 * 注意：最新版本的Room支持更多RxJava相关操作，如删除是否完成观察者
 */
@Dao
interface FormDao : AbsFormDao<FormEntity> {

    @Query("SELECT * FROM form where id=:id")
    fun getFormById(id: Long): FormEntity?

}
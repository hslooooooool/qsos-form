package vip.qsos.form_lib.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author : 华清松
 * 表单实体类
 * @param id 表单ID，自增
 * @param title 表单名称
 * @param notice 表单提示内容
 * @param submitName 表单提交按钮名称
 * @param submitter 表单提交者
 * @param sceneType 表单场景类型
 * @param editable 表单是否可编辑
 */
@Entity(tableName = "form", indices = [Index(value = ["id"], unique = true)])
data class FormEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        var title: String? = null,
        var notice: String? = null,
        var submitName: String? = null,
        var submitter: String? = null,
        var sceneType: String? = null,
        var editable: Boolean = true
) {

    /**表单列表项*/
    @Ignore
    var formItems: List<FormItemEntity>? = arrayListOf()
        get() {
            if (field == null) {
                field = arrayListOf()
            }
            return field
        }

    companion object {
        fun create(form: FormEntity, formItems: List<FormItemEntity>): FormEntity {
            form.formItems = formItems
            return form
        }
    }
}

package vip.qsos.form.model.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

/**
 * @author : 华清松
 * 表单子项实体类
 * @param id 表单ID，自增
 * @param formId 外键-表单id
 * @param title 表单名称
 * @param notice 表单提示内容
 * @param valueType 表单项值类型，0：文本展示；1：输入；2：选项；3：时间；4：人员；5：附件；6：位置
 * @param editable 表单项是否可编辑
 * @param position 表单项顺序
 * @param visible 表单项是否显示
 * @param require 表单项是否必填
 */
@Entity(tableName = "formItem",
        foreignKeys = [
            ForeignKey(entity = FormEntity::class, parentColumns = ["id"], childColumns = ["formId"], onDelete = CASCADE)
        ],
        indices = [
            Index(value = ["id"], unique = true)
        ]
)
data class FormItem constructor(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        var formId: Long? = null,
        var title: String = "",
        var notice: String? = null,
        var valueType: Int = 0,
        var editable: Boolean = true,
        var position: Int = 0,
        var visible: Boolean = true,
        var require: Boolean = false
) {
    /**表单项值列表项*/
    @Embedded
    var formItemValue: FormItemValue? = null
        get() = if (field == null) FormItemValue() else field

    companion object {
        fun newFormItemValue(item: FormItem, value: FormItemValue): FormItem {
            item.formItemValue = value
            return item
        }
    }
}
package vip.qsos.form.lib.model

import vip.qsos.form.lib.helper.FormTransHelper

/**表单项值实体类
 * @author : 华清松
 */
class FormValueEntity {
    constructor()

    /**
     * @param limitFormat 限制条件
     * @param editable 是否可编辑
     * @param position 顺序
     * @param valueType 表单项值类型，如：0：文本展示；1：文本输入；2：选项；3：时间；4：人员；5：文件；6：位置
     * */
    constructor(
            valueType: Int,
            limitFormat: String? = null,
            editable: Boolean = true,
            position: Int = 1
    ) {
        this.valueType = valueType
        this.limitFormat = limitFormat
        this.editable = editable
        this.position = position
    }

    var valueType: Int = -1
        set(v) {
            field = v
            value?.valueType = field
        }

    var limitFormat: String? = null
    var editable: Boolean = true
    var position: Int = 1
    var formValue: String? = null

    var value: ValueEntity? = null
        set(value) {
            field = value
            this.formValue = FormTransHelper.transValue(value)
        }
        get() {
            return when {
                field != null -> field
                formValue != null -> {
                    field = FormTransHelper.tansValue(valueType, formValue!!)
                    field
                }
                else -> null
            }
        }

    fun transValue() {
        this.formValue = FormTransHelper.transValue(value)
    }
}
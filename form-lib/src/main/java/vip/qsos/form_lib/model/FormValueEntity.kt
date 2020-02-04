package vip.qsos.form_lib.model

/**
 * @author : 华清松
 * 表单项值实体类
 * @param id 表单项值ID
 * @param formItemId 外键-表单项ID
 * @param limit 限制条件
 * @param editable 是否可编辑
 * @param position 顺序
 */
data class FormValueEntity<T : FormValueType> constructor(
        var id: Long? = null,
        var formItemId: Long? = null,
        var limit: String? = null,
        var editable: Boolean = true,
        var position: Int = 1
) : AbsFormValue<T>()
package vip.qsos.form_lib.model

/**
 * @author : 华清松
 * 表单项值实体类
 * @param limit 限制条件
 * @param editable 是否可编辑
 * @param position 顺序
 */
data class FormValueEntity<T : AbsValue> constructor(
        var limit: String? = null,
        var editable: Boolean = true,
        var position: Int = 1,
        /**真实值对象*/
        var value: T? = null
)
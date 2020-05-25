package vip.qsos.form.lib.model

/**表单项值实体类
 * @author : 华清松
 * @param limit 限制条件
 * @param editable 是否可编辑
 * @param position 顺序
 * @param value 真实值对象
 */
data class FormValueEntity<T : AbsValue> constructor(
        var limit: String? = null,
        var editable: Boolean = true,
        var position: Int = 1,
        var value: T
)
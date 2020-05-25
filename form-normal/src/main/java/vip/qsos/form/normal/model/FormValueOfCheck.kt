package vip.qsos.form.normal.model

import vip.qsos.form.lib.model.AbsValue

/**表单项值-选项实体类
 * @author : 华清松
 * @param ckId 选项ID
 * @param ckName 选项名称
 * @param ckValue 选项值
 * @param ckChecked 是否被选
 */
data class FormValueOfCheck(
        var ckId: String? = null,
        var ckName: String,
        var ckValue: String = ckName,
        var ckChecked: Boolean = false
) : AbsValue()
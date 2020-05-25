package vip.qsos.form.normal.model

import vip.qsos.form.lib.model.AbsValue

/**表单项值-文本实体类
 * @author : 华清松
 * @param content 输入内容
 */
data class FormValueOfText(
        var content: String? = ""
) : AbsValue()
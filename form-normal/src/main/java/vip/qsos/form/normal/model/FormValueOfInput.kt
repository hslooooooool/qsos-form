package vip.qsos.form.normal.model

import com.google.gson.Gson

/**表单项值-文本输入实体类
 * @author : 华清松
 * @param content 输入内容
 */
data class FormValueOfInput(
        var content: String? = ""
) : AbsValue() {
    override val valueType: Int = 1
}
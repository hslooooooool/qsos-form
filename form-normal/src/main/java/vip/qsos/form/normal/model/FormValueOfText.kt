package vip.qsos.form.normal.model

/**表单项值-文本展示实体类
 * @author : 华清松
 * @param content 输入内容
 */
data class FormValueOfText(
        var content: String? = ""
) : AbsValue() {
    override val valueType: Int = 0
}
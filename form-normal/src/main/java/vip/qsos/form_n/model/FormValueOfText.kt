package vip.qsos.form_n.model

import vip.qsos.form_lib.model.BaseFormValue

/**
 * @author : 华清松
 * 表单项值-文本实体类
 * @param content 输入内容
 */
data class FormValueOfText(
        var content: String? = ""
) : BaseFormValue<FormValueOfText>(){
    override val type: Int = 1
}
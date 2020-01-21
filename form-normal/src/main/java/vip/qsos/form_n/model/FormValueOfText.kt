package vip.qsos.form_n.model

import com.google.gson.Gson

/**
 * @author : 华清松
 * 表单项值-文本实体类
 * @param content 输入内容
 */
data class FormValueOfText(
        var content: String? = ""
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
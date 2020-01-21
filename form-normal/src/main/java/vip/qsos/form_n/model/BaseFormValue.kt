package vip.qsos.form_n.model

import com.google.gson.Gson
import vip.qsos.form_lib.model.AbsFormValue

/**
 * @author : 华清松
 * 表单项值
 */
open class BaseFormValue<T : AbsFormValue<T>>(private val clazz: Class<T>) : AbsFormValue<T>() {

    override fun getEntity(value: String): T? {
        return Gson().fromJson(value, clazz)
    }

    override fun transValue(): String {
        return Gson().toJson(obj) ?: ""
    }

    override fun transValue(obj: T?): String {
        return Gson().toJson(obj) ?: ""
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
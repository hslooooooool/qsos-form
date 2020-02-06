package vip.qsos.form_lib.model

import java.util.*

/**
 * @author : 华清松
 * 表单项实体类
 * @param title 表单项名称
 * @param notice 表单项提示内容
 * @param valueType 表单项值类型，如：0：文本展示；1：输入；2：选项；3：时间；4：人员；5：文件；6：位置
 * @param editable 表单项是否可编辑
 * @param position 表单项顺序
 * @param visible 表单项是否显示
 * @param require 表单项是否必填
 * @param limitMin 值的最小数量
 * @param limitMax 值的最大数量
 * @param limit 值限制。如：选用户的时候，可为角色字段;选时间的时候,可为时间格式；多个条件可用";"分割，不传不限制
 */
data class FormItemEntity<T : AbsValue> constructor(
        var title: String = "",
        var notice: String? = null,
        var valueType: Int = 0,
        var position: Int = 0,
        var editable: Boolean = true,
        var visible: Boolean = true,
        var require: Boolean = false,
        var limitMin: Int? = 0,
        var limitMax: Int? = 0,
        var limit: String? = ""
) {

    /**表单项值第一个*/
    var formValue: FormValueEntity<T>? = null
        get() = if (formValues.isNullOrEmpty()) null else formValues!![0]
        set(value) {
            field = value
            value?.let { formValues!!.add(0, value) }
        }

    /**表单项值集合*/
    var formValues: ArrayList<FormValueEntity<T>>? = arrayListOf()
        get() {
            if (field == null) {
                field = arrayListOf()
            }
            return field
        }

    /**表单项类型限制集合*/
    var limitTypeList: List<String>? = null
        get() {
            if (field == null) {
                field = limit?.toLowerCase(Locale.ENGLISH)?.split(";") ?: arrayListOf()
            }
            return field
        }

}
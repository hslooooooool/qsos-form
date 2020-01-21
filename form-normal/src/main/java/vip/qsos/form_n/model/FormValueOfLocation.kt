package vip.qsos.form_n.model

/**
 * @author : 华清松
 * 表单项值-位置实体类
 * @param locName 位置名称
 * @param locX 位置经度
 * @param locY 位置维度
 */
data class FormValueOfLocation(
        var locName: String? = null,
        var locX: Double? = null,
        var locY: Double? = null
) : BaseFormValue<FormValueOfLocation>(FormValueOfLocation::class.java)
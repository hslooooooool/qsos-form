package vip.qsos.form_n.model

import vip.qsos.form_lib.model.FormValueType

/**
 * @author : 华清松
 * 表单项值-时间实体类
 * @param timeStart 开始时间毫秒数
 * @param timeEnd 结束时间毫秒数
 * @param timeLimitMin 可选最小时间毫秒数
 * @param timeLimitMax 可选最大时间毫秒数
 */
data class FormValueOfTime(
        var timeStart: Long = 0L,
        var timeEnd: Long? = null,
        var timeLimitMin: Long? = null,
        var timeLimitMax: Long? = null
) : FormValueType()
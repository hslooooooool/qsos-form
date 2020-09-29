package vip.qsos.form.normal.model

/**表单项值-位置实体类
 * @author : 华清松
 * @param locName 位置名称
 * @param locX 位置经度
 * @param locY 位置维度
 */
data class FormValueOfLocation constructor(
        var locName: String? = null,
        var locX: Double,
        var locY: Double
) : AbsValue() {

    override val valueType: Int = 6
}
package vip.qsos.form_lib.model

/**
 * @author : 华清松
 * 表单实体类
 * @param title 表单名称
 * @param notice 表单提示内容
 * @param submitter 表单提交者
 * @param sceneType 表单场景类型
 * @param editable 表单是否可编辑
 */
data class FormEntity(
        var title: String? = null,
        var notice: String? = null,
        var submitter: String? = null,
        var sceneType: String? = null,
        var editable: Boolean = true
) {

    /**表单列表项*/
    var formItems: List<FormItemEntity<*>>? = arrayListOf()
        get() {
            if (field == null) {
                field = arrayListOf()
            }
            return field
        }

}

package vip.qsos.form_lib.model

/**
 * @author : 华清松
 * 表单实体类
 * @param id 表单ID，自增
 * @param title 表单名称
 * @param notice 表单提示内容
 * @param submitName 表单提交按钮名称
 * @param submitter 表单提交者
 * @param sceneType 表单场景类型
 * @param editable 表单是否可编辑
 */
data class FormEntity(
        var id: Long? = null,
        var title: String? = null,
        var notice: String? = null,
        var submitName: String? = null,
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

    companion object {
        fun create(form: FormEntity, formItems: List<FormItemEntity<*>>): FormEntity {
            form.formItems = formItems
            return form
        }
    }
}

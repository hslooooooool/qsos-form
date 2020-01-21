package vip.qsos.form

import vip.qsos.form_lib.model.FormEntity
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_lib.model.FormValueEntity
import vip.qsos.form_n.model.FormValueOfCheck
import vip.qsos.form_n.model.FormValueOfFile
import vip.qsos.form_n.model.FormValueOfText

/**表单转换帮助类*/
object FormUtil {

    /**创建一个表单Demo*/
    object Create {
        /**反馈表单*/
        fun feedbackForm(): FormEntity {
            val form = FormEntity(title = "表单案例", notice = "表单案例", submitName = "提交")
            val formItemList = arrayListOf<FormItemEntity>()
            /**说明*/
            val desc = FormItemEntity(title = "说明", notice = "说明", valueType = 0, editable = false)
            val descValue = FormValueEntity(
                    value = FormValueOfText("填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!").toString()
            )
            desc.formValues!!.add(descValue)
            formItemList.add(desc)
            /**用户*/
            val state = FormItemEntity(title = "用户", notice = "选择用户,必选",
                    valueType = 2, limitMin = 1, limitMax = 1, require = true)
            for (i in 1..2) {
                val stateValue = FormValueEntity(
                        position = i,
                        value = FormValueOfCheck("$i", "用户$i", "$i").toString()
                )
                state.formValues!!.add(stateValue)
                state.formValues!!.sortedBy { it.position }
            }
            formItemList.add(state)
            /**类型*/
            val type = FormItemEntity(title = "类型", notice = "选择类型,方便快速定位问题,必选",
                    valueType = 2, limitMin = 1, limitMax = 3, require = true)
            for (i in 1..20) {
                val typeValue = FormValueEntity(
                        position = i,
                        value = FormValueOfCheck("$i", "类型$i", "$i", i == 4).toString()
                )
                type.formValues!!.add(typeValue)
                type.formValues!!.sortedBy { it.position }
            }
            formItemList.add(type)
            /**内容*/
            val content = FormItemEntity(title = "内容", notice = "请填写内容",
                    valueType = 1, limitMin = 10, limitMax = 200, require = true)
            val contentValue = FormValueEntity(
                    value = FormValueOfText().toString()
            )
            content.formValues!!.add(contentValue)
            formItemList.add(content)
            /**附件上传*/
            val file = FormItemEntity(title = "附件上传", notice = "上传问题截图有助于我们更加快速的定位问题",
                    valueType = 5, limitMin = 0, limitMax = 9, limit = "FILE")
            val fileValue = FormValueEntity(
                    value = FormValueOfFile(fileName = "测试文件").toString()
            )
            file.formValues!!.add(fileValue)
            file.formValues!!.add(fileValue)
            file.formValues!!.add(fileValue)
            file.formValues!!.add(fileValue)
            file.formValues!!.add(fileValue)
            formItemList.add(file)

            form.formItems = formItemList
            return form
        }
    }

}
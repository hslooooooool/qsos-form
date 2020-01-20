package vip.qsos.form

import vip.qsos.form_lib.model.FormEntity
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_lib.model.FormValueEntity
import vip.qsos.form_n.model.FormValueOfCheck
import vip.qsos.form_n.model.FormValueOfText

/**表单转换帮助类*/
object FormUtil {

    /**创建一个表单Demo*/
    object Create {
        /**反馈表单*/
        fun feedbackForm(): FormEntity {
            val form = FormEntity(title = "指令反馈", notice = "指令反馈表单", submitName = "提交反馈")
            val formItemList = arrayListOf<FormItemEntity>()
            /**反馈状态*/
            val state = FormItemEntity(title = "反馈状态", notice = "请选择反馈状态",
                    valueType = 2, limitMin = 1, limitMax = 1)
            val stateValue1 = FormValueEntity(
                    value = FormValueOfCheck("1", "做得好", "YES", true).toString()
            )
            val stateValue2 = FormValueEntity(
                    value = FormValueOfCheck("2", "做得差", "NO", false).toString()
            )
            state.formValues!!.add(stateValue1)
            state.formValues!!.add(stateValue2)
            formItemList.add(state)
            /**反馈内容*/
            val content = FormItemEntity(title = "反馈内容", notice = "请填写反馈意见",
                    valueType = 1, limitMin = 10, limitMax = 200)
            val contentValue = FormValueEntity(
                    value = FormValueOfText().toString()
            )
            content.formValues!!.add(contentValue)
            formItemList.add(content)
            /**反馈附件*/
            val file = FormItemEntity(title = "反馈内容", notice = "请填写反馈意见",
                    valueType = 5, limitMin = 1, limitMax = 3, limit = "IMAGE")
            val fileValue = FormValueEntity()
            file.formValues!!.add(fileValue)
            formItemList.add(file)

            form.formItems = formItemList
            return form
        }
    }

}
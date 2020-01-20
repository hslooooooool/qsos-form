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
            val form = FormEntity(title = "意见反馈", notice = "意见反馈", submitName = "提交")
            val formItemList = arrayListOf<FormItemEntity>()
            /**反馈解释*/
            val desc = FormItemEntity(title = "反馈说明", valueType = 0, editable = false)
            val descValue = FormValueEntity(
                    value = FormValueOfText("填写反馈表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!").toString()
            )
            desc.formValues!!.add(descValue)
            formItemList.add(desc)
            /**反馈类型*/
            val state = FormItemEntity(title = "反馈类型*", notice = "选择反馈类型,方便快速定位问题,必选",
                    valueType = 2, limitMin = 1, limitMax = 1, require = true)
            val stateValue1 = FormValueEntity(
                    value = FormValueOfCheck("1", "产品问题", "1", true).toString()
            )
            val stateValue2 = FormValueEntity(
                    value = FormValueOfCheck("2", "BUG反馈", "2", false).toString()
            )
            state.formValues!!.add(stateValue1)
            state.formValues!!.add(stateValue2)
            formItemList.add(state)
            /**反馈内容*/
            val content = FormItemEntity(title = "反馈内容*", notice = "请填写反馈意见",
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
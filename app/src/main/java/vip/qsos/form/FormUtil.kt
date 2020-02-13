package vip.qsos.form

import vip.qsos.form.lib.model.FormEntity
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.model.FormValueOfCheck
import vip.qsos.form.normal.model.FormValueOfFile
import vip.qsos.form.normal.model.FormValueOfText
import vip.qsos.form.normal.model.FormValueOfTime

/**表单帮助类*/
object FormUtil {

    /**创建一个表单Demo*/
    object Create {
        /**反馈表单*/
        fun feedbackForm(): FormEntity {
            val form = FormEntity(title = "表单案例", notice = "这是一个表单填写案例")
            val formItemList = arrayListOf<FormItemEntity<*>>()

            /**说明*/
            val desc1 = FormItemEntity<FormValueOfText>(title = "文本举例1", notice = "文本举例1",
                    valueType = 0, editable = false)
            val descValue1 = FormValueEntity<FormValueOfText>().also {
                it.editable = false
                it.value = FormValueOfText("填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!")
            }
            desc1.formValues!!.add(descValue1)
            formItemList.add(desc1)

            /**说明*/
            val desc2 = FormItemEntity<FormValueOfText>(title = "文本举例2", notice = "文本举例2",
                    valueType = 0, editable = false)
            val descValue2 = FormValueEntity<FormValueOfText>().also {
                it.editable = false
                it.value = FormValueOfText("填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!")
            }
            desc2.formValues!!.add(descValue2)
            formItemList.add(desc2)

            /**内容*/
            val content1 = FormItemEntity<FormValueOfText>(title = "内容举例1", notice = "请填写内容，必填",
                    valueType = 1, limitMin = 10, limitMax = 100, require = true)
            val contentValue1 = FormValueEntity<FormValueOfText>()
            contentValue1.value = FormValueOfText()
            content1.formValues!!.add(contentValue1)
            formItemList.add(content1)

            /**内容*/
            val content2 = FormItemEntity<FormValueOfText>(title = "内容举例2", notice = "请填写内容，非必填",
                    valueType = 1, limitMin = 0, limitMax = 200, require = false)
            val contentValue2 = FormValueEntity<FormValueOfText>()
            contentValue2.value = FormValueOfText()
            content2.formValues!!.add(contentValue2)
            formItemList.add(content2)

            /**单选举例*/
            val state1 = FormItemEntity<FormValueOfCheck>(title = "单选举例1", notice = "单选举例,必选",
                    valueType = 2, limitMin = 1, limitMax = 1, require = true)
            for (i in 1..2) {
                val stateValue = FormValueEntity<FormValueOfCheck>(position = i)
                stateValue.value = FormValueOfCheck("$i", "选项$i", "$i")
                state1.formValues!!.add(stateValue)
                state1.formValues!!.sortedBy { it.position }
            }
            formItemList.add(state1)

            /**单选举例*/
            val state2 = FormItemEntity<FormValueOfCheck>(title = "单选举例2", notice = "单选举例,非必选",
                    valueType = 2, limitMin = 1, limitMax = 1, require = false)
            for (i in 1..4) {
                val stateValue = FormValueEntity<FormValueOfCheck>(position = i)
                stateValue.value = FormValueOfCheck("$i", "选项$i", "$i")
                state2.formValues!!.add(stateValue)
                state2.formValues!!.sortedBy { it.position }
            }
            formItemList.add(state2)

            /**多选举例*/
            val type1 = FormItemEntity<FormValueOfCheck>(title = "多选举例1", notice = "多选举例,必选",
                    valueType = 2, limitMin = 1, limitMax = 3, require = true)
            for (i in 1..7) {
                val typeValue = FormValueEntity<FormValueOfCheck>(position = i)
                typeValue.value = FormValueOfCheck("$i", "选项$i", "$i", i == 1)
                type1.formValues!!.add(typeValue)
                type1.formValues!!.sortedBy { it.position }
            }
            formItemList.add(type1)

            /**多选举例*/
            val type2 = FormItemEntity<FormValueOfCheck>(title = "多选举例2", notice = "多选举例,非必选",
                    valueType = 2, limitMin = 0, limitMax = 0, require = false)
            for (i in 1..5) {
                val typeValue = FormValueEntity<FormValueOfCheck>(position = i)
                typeValue.value = FormValueOfCheck("$i", "选项$i", "$i", false)
                type2.formValues!!.add(typeValue)
                type2.formValues!!.sortedBy { it.position }
            }
            formItemList.add(type2)

            /**时间举例*/
            val nowTime = System.currentTimeMillis()
            val limitTime = 5L * 365 * 60 * 24 * 1000 * 60
            val time1 = FormItemEntity<FormValueOfTime>(title = "时间举例1", notice = "时间举例,必选",
                    valueType = 3, require = true)
            val timeValue1 = FormValueEntity<FormValueOfTime>(position = 1, limit = "yyyy-MM-dd HH:mm")
            timeValue1.value = FormValueOfTime(nowTime, null,
                    nowTime - limitTime,
                    nowTime + limitTime)
            time1.formValues!!.add(timeValue1)
            time1.formValues!!.sortedBy { it.position }
            formItemList.add(time1)

            /**时间举例*/
            val time2 = FormItemEntity<FormValueOfTime>(title = "时间举例2", notice = "时间举例,非必选",
                    valueType = 3, editable = false)
            val timeValue2 = FormValueEntity<FormValueOfTime>(position = 1, limit = "yyyy-MM-dd HH:mm")
            timeValue2.value = FormValueOfTime(nowTime, null,
                    nowTime - limitTime,
                    nowTime + limitTime)
            time2.formValues!!.add(timeValue2)
            time2.formValues!!.sortedBy { it.position }
            formItemList.add(time2)

            /**附件上传*/
            val file = FormItemEntity<FormValueOfFile>(title = "附件上传", notice = "上传问题截图有助于我们更加快速的定位问题",
                    valueType = 5, limitMin = 0, limitMax = 9, limit = "FILE")
            for (i in 1..5) {
                val fileValue = FormValueEntity<FormValueOfFile>()
                fileValue.value = FormValueOfFile(fileName = "测试文件$i")
                file.formValues!!.add(fileValue)
            }
            formItemList.add(file)

            form.formItems = formItemList
            return form
        }
    }

}
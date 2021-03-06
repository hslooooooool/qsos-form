package vip.qsos.form

import vip.qsos.form.lib.model.FormEntity
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.lib.model.ValueEntity

/**表单帮助类*/
object FormUtil {

    /**创建一个表单Demo*/
    object Create {
        /**反馈表单*/
        fun feedbackForm(): FormEntity {
            val form = FormEntity(title = "表单案例", notice = "这是一个表单填写案例")
            val formItemList = arrayListOf<FormItemEntity>()

            /**说明*/
            val desc1 = FormItemEntity(title = "文本举例1", notice = "文本举例1",
                    valueType = 0, editable = false)
            val descValue1 = FormValueEntity(0)
            descValue1.value = ValueEntity("填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!")
            descValue1.editable = false
            desc1.formValues.add(descValue1)
            formItemList.add(desc1)

            /**说明*/
            val desc2 = FormItemEntity(title = "文本举例2", notice = "文本举例2",
                    valueType = 0, editable = false)
            val descValue2 = FormValueEntity(0)
            descValue2.value = ValueEntity("填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!填写表单,帮助我们解决问题,您将有机会获得优惠券,感谢支持!")
            descValue2.editable = false
            desc2.formValues.add(descValue2)
            formItemList.add(desc2)

            /**内容*/
            val content1 = FormItemEntity(title = "内容举例1", notice = "请填写内容，必填",
                    valueType = 1, limitMin = 5, limitMax = 100, require = true)
            val contentValue1 = FormValueEntity(1)
            contentValue1.value = ValueEntity()
            content1.formValues.add(contentValue1)
            formItemList.add(content1)

            /**内容*/
            val content2 = FormItemEntity(title = "内容举例2", notice = "请填写内容，非必填",
                    valueType = 1, limitMin = 0, limitMax = 200, require = false)
            formItemList.add(content2)

            /**单选举例*/
            val state1 = FormItemEntity(title = "单选举例1", notice = "单选举例,必选",
                    valueType = 2, limitMin = 1, limitMax = 1, require = true)
            for (i in 1..2) {
                val stateValue = FormValueEntity(2, position = i)
                stateValue.value = ValueEntity("$i", "选项$i", "$i", false)
                state1.formValues.add(stateValue)
                state1.formValues.sortedBy { it.position }
            }
            formItemList.add(state1)

            /**单选举例*/
            val state2 = FormItemEntity(title = "单选举例2", notice = "单选举例,非必选",
                    valueType = 2, limitMin = 1, limitMax = 1, require = false)
            for (i in 1..4) {
                val stateValue = FormValueEntity(2, position = i)
                stateValue.value = ValueEntity("$i", "选项$i", "$i", false)
                state2.formValues.add(stateValue)
                state2.formValues.sortedBy { it.position }
            }
            formItemList.add(state2)

            /**位置举例*/
            val location = FormItemEntity(title = "位置举例", notice = "位置举例,必选",
                    valueType = 6, limitMin = 1, limitMax = 1, require = true)
            formItemList.add(location)

            /**多选举例*/
            val type1 = FormItemEntity(title = "多选举例1", notice = "多选举例,必选",
                    valueType = 2, limitMin = 1, limitMax = 3, require = true)
            for (i in 1..7) {
                val typeValue = FormValueEntity(2, position = i)
                typeValue.value = ValueEntity("$i", "选项$i", "$i", false)
                type1.formValues.add(typeValue)
                type1.formValues.sortedBy { it.position }
            }
            formItemList.add(type1)

            /**多选举例*/
            val type2 = FormItemEntity(title = "多选举例2", notice = "多选举例,非必选",
                    valueType = 2, limitMin = 0, limitMax = 0, require = false)
            for (i in 1..5) {
                val typeValue = FormValueEntity(2, position = i)
                typeValue.value = ValueEntity("$i", "选项$i", "$i", false)
                type2.formValues.add(typeValue)
                type2.formValues.sortedBy { it.position }
            }
            formItemList.add(type2)

            /**时间举例*/
            val nowTime = System.currentTimeMillis()
            val limitTime = 5L * 365 * 60 * 24 * 1000 * 60
            val time1 = FormItemEntity(title = "时间举例1", notice = "时间举例,必选",
                    valueType = 3, require = true)
            val timeValue1 = FormValueEntity(3, position = 1, limitFormat = "yyyy-MM-dd HH:mm")
            timeValue1.value = ValueEntity(nowTime, nowTime - limitTime, nowTime + limitTime)
            time1.formValues.add(timeValue1)
            time1.formValues.sortedBy { it.position }
            formItemList.add(time1)

            /**时间举例*/
            val time2 = FormItemEntity(title = "时间举例2", notice = "时间举例,非必选",
                    valueType = 3, editable = false)
            val timeValue2 = FormValueEntity(3, position = 1, limitFormat = "yyyy-MM-dd HH:mm")
            timeValue2.value = ValueEntity(nowTime, nowTime - limitTime, nowTime + limitTime)
            time2.formValues.add(timeValue2)
            time2.formValues.sortedBy { it.position }
            formItemList.add(time2)

            /**附件上传*/
            val file = FormItemEntity(title = "附件举例1", notice = "全是图片",
                    valueType = 5, limitMin = 0, limitMax = 9, limitFormat = "IMAGE")
            val fileValue = FormValueEntity(5)
            fileValue.value = ValueEntity(fileName = "测试图片", fileUrl = "http://www.qsos.vip/upload/2018/11/ic_launcher20181225044818498.png")
            file.formValues.add(fileValue)
            formItemList.add(file)

            /**附件上传*/
            val file2 = FormItemEntity(title = "附件举例2", notice = "所有文件",
                    valueType = 5, limitMin = 0, limitMax = 9, limitFormat = "FILE")
            for (i in 1..2) {
                val file2Value = FormValueEntity(5)
                file2Value.value = ValueEntity(fileName = "测试文件$i", fileUrl = "http://www.qsos.vip/upload/2018/11/ic_launcher20181225044818498.png")
                file2Value.editable = i != 1
                file2.formValues.add(file2Value)
            }
            formItemList.add(file2)

            /**人员举例*/
            val user = FormItemEntity(title = "人员举例1", notice = "管理员，至少一人",
                    valueType = 4, limitMin = 1, limitFormat = "ADMIN", require = true)
            for (i in 1..2) {
                val userValue = FormValueEntity(4)
                userValue.value = ValueEntity(userName = "管理人员$i", userAvatar = "http://www.qsos.vip/upload/2018/11/ic_launcher20181225044818498.png")
                user.formValues.add(userValue)
            }
            formItemList.add(user)

            /**人员举例*/
            val user2 = FormItemEntity(title = "人员举例2", notice = "抄送人员，必须抄送给抄送人1", valueType = 4)
            for (i in 1..3) {
                val value = FormValueEntity(4)
                value.value = ValueEntity(userName = "抄送人员$i", userAvatar = "http://www.qsos.vip/upload/2018/11/ic_launcher20181225044818498.png")
                value.editable = i != 1
                user2.formValues.add(value)
            }
            formItemList.add(user2)

            form.formItems = formItemList
            return form
        }
    }

}
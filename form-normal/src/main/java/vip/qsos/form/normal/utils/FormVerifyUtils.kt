package vip.qsos.form.normal.utils

import android.text.TextUtils
import vip.qsos.form.lib.model.FormEntity
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.model.*

/**表单填写结果校验 工具类
 * @author : 华清松
 */
object FormVerifyUtils {
    /**校验信息*/
    class Verify {
        var pass = true

        /**错误类型：0 无错 1必填未填 2已填为空 3已填格式错误 4已填数量不够 5已填数量多于*/
        var type: Int = 0
        var info: Info = Info(0)

        val msg: String
            get() {
                val info = "[Item]${info.itemIndex}[Value]${info.valueIndex}-"
                return when (type) {
                    0 -> "较验通过"
                    1 -> info + "没有设置"
                    2 -> info + "没有值"
                    3 -> info + "格式错误"
                    4 -> info + "大小不足"
                    5 -> info + "大小超标"
                    else -> info + "较验失败"
                }
            }

        data class Info(
                val itemIndex: Int,
                var valueIndex: Int = -1
        )

    }

    /**校验方法*/
    fun verify(form: FormEntity): Verify {
        val verify = Verify()
        form.formItems.forEachIndexed { index, formItem ->
            if (!verify.pass) {
                return verify
            }
            verify.info = Verify.Info(index)
            val required = formItem.require
            val nullValue = formItem.formValues.isNullOrEmpty()
            if (required && nullValue) {
                /*必填，未填*/
                verify.pass = false
                verify.type = 1
            } else {
                /*表单项值类型，0：文本展示；1：输入；2：选项；3：时间；4：人员；5：附件；6：位置*/
                when (formItem.valueType) {
                    1 -> input(verify, formItem as FormItemEntity<FormValueOfText>)
                    2 -> chose(verify, formItem as FormItemEntity<FormValueOfCheck>)
                    3 -> time(verify, formItem as FormItemEntity<FormValueOfTime>)
                    4 -> user(verify, formItem as FormItemEntity<FormValueOfUser>)
                    5 -> file(verify, formItem as FormItemEntity<FormValueOfFile>)
                    6 -> location(verify, formItem as FormItemEntity<FormValueOfLocation>)
                }
            }

        }
        return verify
    }

    /**输入校验*/
    private fun input(verify: Verify, formItem: FormItemEntity<FormValueOfText>, regex: Boolean = true) {
        formItem.formValues.forEachIndexed { index, formValue ->
            if (verify.pass) {
                verify.info.valueIndex = index
                val content = formValue.value.content?.trim()
                /*判断文字是否为空*/
                if (formItem.require && TextUtils.isEmpty(content)) {
                    verify.pass = false
                    verify.type = 2
                }
                if (verify.pass) {
                    var typeRight = true
                    /*已填，判断文字是否符合输入限制*/
                    if (regex) {
                        formValue.limit?.let {
                            typeRight = try {
                                Regex(it).matches(content!!)
                            } catch (e: Exception) {
                                false
                            }
                        }
                    }
                    if (!typeRight) {
                        verify.pass = false
                        verify.type = 3
                    }
                }
                if (verify.pass) {
                    if (formItem.limitMin > 0 && formValue.value.content!!.length < formItem.limitMin) {
                        /*已填，判断最小输入字数是否满足*/
                        verify.pass = false
                        verify.type = 4
                    }
                }
                if (verify.pass) {
                    if (formItem.limitMax > 0 && formValue.value.content!!.length > formItem.limitMax) {
                        /*已填，判断最小输入字数是否满足*/
                        verify.pass = false
                        verify.type = 5
                    }
                }
            }
        }
    }

    /**选项校验*/
    private fun chose(verify: Verify, formItem: FormItemEntity<FormValueOfCheck>) {
        if (formItem.limitMax == 1) {
            /*单选*/
            var chose = 0
            formItem.formValues.forEach {
                if (it.value.ckChecked) {
                    chose++
                }
            }
            if (formItem.require) {
                if (formItem.limitMin > 0 && formItem.limitMin > chose) {
                    verify.pass = false
                    verify.type = 4
                }
            }
            if (verify.pass) {
                if (chose > formItem.limitMax) {
                    verify.pass = false
                    verify.type = 5
                }
            }
        } else {
            /*多选*/
            var chose = 0
            formItem.formValues.forEach {
                if (it.value.ckChecked) {
                    chose++
                }
            }
            if (formItem.require) {
                if (formItem.limitMin > 0 && formItem.limitMin > chose) {
                    verify.pass = false
                    verify.type = 4
                }
            }
            if (verify.pass) {
                if (formItem.limitMax in 1 until chose) {
                    verify.pass = false
                    verify.type = 5
                }
            }
        }
    }

    /**时间校验*/
    private fun time(verify: Verify, formItem: FormItemEntity<FormValueOfTime>) {
        formItem.formValues.forEachIndexed { index, formValue ->
            if (verify.pass) {
                verify.info.valueIndex = index
                val time = formValue.value.time
                if (formItem.require && time <= 0L) {
                    verify.pass = false
                    verify.type = 2
                }
                if (verify.pass && time > 0L) {
                    if (formValue.value.timeLimitMin > 0 && formValue.value.timeLimitMin > time) {
                        verify.pass = false
                        verify.type = 4
                    }
                    if (verify.pass) {
                        if (formValue.value.timeLimitMax in 1 until time) {
                            verify.pass = false
                            verify.type = 5
                        }
                    }
                }
            }
        }
    }

    /**人员校验*/
    private fun user(verify: Verify, formItem: FormItemEntity<FormValueOfUser>) {
        val size = formItem.formValues.size
        if (size > 0) {
            /*不为空*/
            if (formItem.limitMin > 0 && size < formItem.limitMin) {
                verify.pass = false
                verify.type = 4
            }
            if (verify.pass) {
                if (formItem.limitMax in 1 until size) {
                    verify.pass = false
                    verify.type = 5
                }
            }
        }
    }

    /**文件校验*/
    private fun file(verify: Verify, formItem: FormItemEntity<FormValueOfFile>) {
        val size = formItem.formValues.size
        if (size > 0) {
            formItem.formValues.forEachIndexed { index, formValueEntity ->
                if (verify.pass) {
                    verify.info.valueIndex = index
                    if (TextUtils.isEmpty(formValueEntity.value.fileUrl)) {
                        verify.pass = false
                        verify.type = 2
                    }
                }
            }

            if (verify.pass) {
                if (formItem.limitMin > 0 && size < formItem.limitMin) {
                    verify.pass = false
                    verify.type = 4
                }
            }

            if (verify.pass) {
                if (formItem.limitMax in 1 until size) {
                    verify.pass = false
                    verify.type = 5
                }
            }
        }
    }

    /**位置校验*/
    private fun location(verify: Verify, formItem: FormItemEntity<FormValueOfLocation>) {
        val size = formItem.formValues.size
        if (size > 0) {
            /*不为空*/
            if (formItem.limitMin > 0 && size < formItem.limitMin) {
                verify.pass = false
                verify.type = 4
            }
            if (verify.pass) {
                if (formItem.limitMax in 1 until size) {
                    verify.pass = false
                    verify.type = 5
                }
            }
        }
    }
}
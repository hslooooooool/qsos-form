package vip.qsos.form.normal.utils

import android.text.TextUtils
import vip.qsos.form.lib.model.FormEntity
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.model.FormValueOfCheck
import vip.qsos.form.normal.model.FormValueOfFile
import vip.qsos.form.normal.model.FormValueOfInput
import vip.qsos.form.normal.model.FormValueOfTime

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
                    1 -> input(verify, formItem)
                    2 -> chose(verify, formItem)
                    3 -> time(verify, formItem)
                    4 -> user(verify, formItem)
                    5 -> file(verify, formItem)
                    6 -> location(verify, formItem)
                }
            }

        }
        return verify
    }

    /**输入校验*/
    private fun input(verify: Verify, formItem: FormItemEntity, regex: Boolean = true) {
        formItem.formValues.forEachIndexed { index, formValue ->
            val v = formValue.value as FormValueOfInput
            if (verify.pass) {
                verify.info.valueIndex = index
                val content = v.content?.trim()
                /*判断文字是否为空*/
                if (formItem.require && TextUtils.isEmpty(content)) {
                    verify.pass = false
                    verify.type = 2
                }
                if (verify.pass) {
                    var typeRight = true
                    /*已填，判断文字是否符合输入限制*/
                    if (regex) {
                        formValue.limitFormat?.let {
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
                    if (formItem.limitMin > 0 && v.content!!.length < formItem.limitMin) {
                        /*已填，判断最小输入字数是否满足*/
                        verify.pass = false
                        verify.type = 4
                    }
                }
                if (verify.pass) {
                    if (formItem.limitMax > 0 && v.content!!.length > formItem.limitMax) {
                        /*已填，判断最小输入字数是否满足*/
                        verify.pass = false
                        verify.type = 5
                    }
                }
            }
        }
    }

    /**选项校验*/
    private fun chose(verify: Verify, formItem: FormItemEntity) {
        if (formItem.limitMax == 1) {
            /*单选*/
            var chose = 0
            formItem.formValues.forEach {
                val v = it.value as FormValueOfCheck
                if (v.ckChecked) {
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
                val v = it.value as FormValueOfCheck
                if (v.ckChecked) {
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
    private fun time(verify: Verify, formItem: FormItemEntity) {
        formItem.formValues.forEachIndexed { index, formValue ->
            if (verify.pass) {
                verify.info.valueIndex = index
                val v = formValue.value as FormValueOfTime
                val time = v.time
                if (formItem.require && time <= 0L) {
                    verify.pass = false
                    verify.type = 2
                }
                if (verify.pass && time > 0L) {
                    if (v.timeLimitMin > 0 && v.timeLimitMin > time) {
                        verify.pass = false
                        verify.type = 4
                    }
                    if (verify.pass) {
                        if (v.timeLimitMax in 1 until time) {
                            verify.pass = false
                            verify.type = 5
                        }
                    }
                }
            }
        }
    }

    /**人员校验*/
    private fun user(verify: Verify, formItem: FormItemEntity) {
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
    private fun file(verify: Verify, formItem: FormItemEntity) {
        val size = formItem.formValues.size
        if (size > 0) {
            formItem.formValues.forEachIndexed { index, formValueEntity ->
                val v = formValueEntity.value as FormValueOfFile
                if (verify.pass) {
                    verify.info.valueIndex = index
                    if (TextUtils.isEmpty(v.fileUrl)) {
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
    private fun location(verify: Verify, formItem: FormItemEntity) {
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
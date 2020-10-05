package vip.qsos.form.lib.helper

import vip.qsos.form.lib.model.FormEntity
import vip.qsos.form.lib.model.FormItemEntity

object FormVerifyHelper : IFormVerify {
    private var helper: IFormVerify? = null

    fun init(helper: IFormVerify) {
        FormVerifyHelper.helper = helper
    }

    override fun verify(form: FormEntity): FormVerify {
        return helper?.verify(form) ?: FormVerify(false)
    }

    override fun input(verify: FormVerify, formItem: FormItemEntity, regex: Boolean) {
        helper?.input(verify, formItem, regex)
    }

    override fun chose(verify: FormVerify, formItem: FormItemEntity) {
        helper?.chose(verify, formItem)
    }

    override fun time(verify: FormVerify, formItem: FormItemEntity) {
        helper?.time(verify, formItem)
    }

    override fun user(verify: FormVerify, formItem: FormItemEntity) {
        helper?.user(verify, formItem)
    }

    override fun file(verify: FormVerify, formItem: FormItemEntity) {
        helper?.file(verify, formItem)
    }

    override fun location(verify: FormVerify, formItem: FormItemEntity) {
        helper?.location(verify, formItem)
    }

}

interface IFormVerify {
    /**校验方法*/
    fun verify(form: FormEntity): FormVerify

    /**输入校验*/
    fun input(verify: FormVerify, formItem: FormItemEntity, regex: Boolean = true)

    /**选项校验*/
    fun chose(verify: FormVerify, formItem: FormItemEntity)

    /**时间校验*/
    fun time(verify: FormVerify, formItem: FormItemEntity)

    /**人员校验*/
    fun user(verify: FormVerify, formItem: FormItemEntity)

    /**文件校验*/
    fun file(verify: FormVerify, formItem: FormItemEntity)

    /**位置校验*/
    fun location(verify: FormVerify, formItem: FormItemEntity)
}

class FormVerify {
    constructor()
    constructor(pass: Boolean) {
        this.pass = pass
    }

    var pass = true

    /**错误类型：0 无错 1必填未填 2已填为空 3已填格式错误 4已填数量不够 5已填数量多于*/
    var type: Int = 0

    /**校验信息*/
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

    /**校验信息
     * @param itemIndex 表单项下标
     * @param valueIndex 表单项值下标
     * */
    data class Info(
            val itemIndex: Int,
            var valueIndex: Int = -1
    )

}
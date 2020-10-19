package vip.qsos.form.lib.helper

import vip.qsos.form.lib.model.ValueEntity

object FormTransHelper : IFormTrans {
    private var helper: IFormTrans? = null

    fun init(helper: IFormTrans) {
        FormTransHelper.helper = helper
    }

    override fun tansValue(valueType: Int, json: String): ValueEntity {
        return helper?.tansValue(valueType, json) ?: ValueEntity()
    }

    override fun transValue(value: ValueEntity?): String {
        return helper?.transValue(value) ?: ""
    }
}

interface IFormTrans {
    fun transValue(value: ValueEntity?): String
    fun tansValue(valueType: Int, json: String): ValueEntity
}
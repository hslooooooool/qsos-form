package vip.qsos.form.lib

import vip.qsos.form.lib.model.EmptyValue
import vip.qsos.form.lib.model.IValue

object FormTransHelper : IFormTransUtils {
    private var helper: IFormTransUtils? = null

    fun init(helper: IFormTransUtils) {
        FormTransHelper.helper = helper
    }

    override fun tansValue(valueType: Int, json: String): IValue {
        return helper?.tansValue(valueType, json) ?: EmptyValue()
    }

    override fun transValue(value: IValue?): String {
        return helper?.transValue(value) ?: ""
    }
}

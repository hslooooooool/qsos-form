package vip.qsos.form.lib

import vip.qsos.form.lib.model.IValue

interface IFormTransUtils {
    fun transValue(value: IValue?): String
    fun tansValue(valueType: Int, json: String): IValue
}
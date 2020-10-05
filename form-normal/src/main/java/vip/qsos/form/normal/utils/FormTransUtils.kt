package vip.qsos.form.normal.utils

import com.google.gson.Gson
import vip.qsos.form.lib.helper.IFormTrans
import vip.qsos.form.lib.model.ValueEntity

class FormTransUtils : IFormTrans {

    override fun transValue(value: ValueEntity?): String {
        return Gson().toJson(value)
    }

    override fun tansValue(valueType: Int, json: String): ValueEntity {
        return Gson().fromJson(json, ValueEntity::class.java)
    }
}
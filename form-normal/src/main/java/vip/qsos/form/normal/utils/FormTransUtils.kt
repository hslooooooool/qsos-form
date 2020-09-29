package vip.qsos.form.normal.utils

import com.google.gson.Gson
import vip.qsos.form.lib.IFormTransUtils
import vip.qsos.form.lib.model.EmptyValue
import vip.qsos.form.lib.model.IValue
import vip.qsos.form.normal.model.*

class FormTransUtils : IFormTransUtils {

    override fun transValue(value: IValue?): String {
        return Gson().toJson(value)
    }

    override fun tansValue(valueType: Int, json: String): IValue {

        val map = Gson().fromJson(json, HashMap::class.java)
        return when (valueType) {
            0 -> {
                val v = FormValueOfText()
                v.content = map["content"] as String
                v
            }
            1 -> {
                val v = FormValueOfInput()
                v.content = map["content"] as String
                v
            }
            2 -> {
                val v = FormValueOfCheck(ckName = "")
                v.ckId = map["ckId"] as String?
                v.ckName = map["ckName"] as String
                v.ckValue = map["ckValue"] as String
                v.ckChecked = map["ckChecked"] as Boolean
                v
            }
            3 -> {
                val v = FormValueOfTime()
                v.time = map["time"] as Long
                v.timeLimitMin = map["timeLimitMin"] as Long
                v.timeLimitMax = map["timeLimitMax"] as Long
                v
            }
            4 -> {
                val v = FormValueOfUser(userName = "")
                v.userId = map["userId"] as String?
                v.userName = map["userName"] as String
                v.userDesc = map["userDesc"] as String
                v.userAvatar = map["userAvatar"] as String?
                v
            }
            5 -> {
                val v = FormValueOfFile()
                v.fileId = map["fileId"] as String?
                v.fileName = map["fileName"] as String
                v.fileType = map["fileType"] as FormValueOfFile.Type
                v.filePath = map["filePath"] as String?
                v.fileUrl = map["fileUrl"] as String?
                v.fileCover = map["fileCover"] as String?
                v
            }
            6 -> {
                val v = FormValueOfLocation(locX = 1.1, locY = 1.1)
                v.locName = map["locName"] as String?
                v.locX = map["locX"] as Double
                v.locY = map["locY"] as Double
                v
            }
            else -> EmptyValue()
        }
    }
}
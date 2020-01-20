package vip.qsos.form_n.utils

import android.text.TextUtils
import com.google.gson.Gson

object FormValueUtil {

    fun <T> getValue(value: String?, clazz: Class<T>): T? {
        return if (TextUtils.isEmpty(value)) {
            null
        } else {
            try {
                Gson().fromJson<T>(value, clazz)
            } catch (e: Exception) {
                null
            }
        }
    }
}
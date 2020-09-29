package vip.qsos.form.normal.model

import com.google.gson.Gson
import vip.qsos.form.lib.model.IValue

/**自定义的表单项值需继承此类
 * @author : 华清松
 */
abstract class AbsValue : HashMap<String, Any>(), IValue {
    override fun json(): String? {
        return Gson().toJson(this)
    }
}
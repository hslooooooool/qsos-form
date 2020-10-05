package vip.qsos.form.holder

import android.view.View
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.lib.model.ValueEntity
import vip.qsos.form.normal.hodler.AbsFormItemLocationHolder
import kotlin.random.Random

/**位置类型视图
 * @author : 华清松
 */
class FormItemLocationHolder(itemView: View) : AbsFormItemLocationHolder(itemView) {

    override fun selectLocation(position: Int, data: FormItemEntity, listener: OnTListener<Boolean>) {
        val value = FormValueEntity(6)
        value.value = ValueEntity(locName = "测试地址${Random.nextInt(10000)}", locX = 134.00, locY = 32.00)
        data.formValue = value
        listener.back(true)
    }
}

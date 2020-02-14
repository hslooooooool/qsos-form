package vip.qsos.form.holder

import android.view.View
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.hodler.AbsFormItemLocationHolder
import vip.qsos.form.normal.model.FormValueOfLocation
import vip.qsos.lib.select.OnSelectListener
import kotlin.random.Random

/**
 * @author : 华清松
 *
 * 位置类型视图
 */
class FormItemLocationHolder(itemView: View) : AbsFormItemLocationHolder(itemView) {

    override fun selectLocation(position: Int, data: FormItemEntity<FormValueOfLocation>, listener: OnSelectListener<Boolean>) {
        val value = FormValueEntity<FormValueOfLocation>()
        value.value = FormValueOfLocation(locName = "测试地址${Random.nextInt(10000)}", locX = 134.00, locY = 32.00)
        data.formValue = value
        listener.select(true)
    }

}

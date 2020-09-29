package vip.qsos.form.holder

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import vip.qsos.form.R
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.hodler.AbsFormItemLocationHolder
import vip.qsos.form.normal.model.FormValueOfLocation
import kotlin.random.Random

/**位置类型视图
 * @author : 华清松
 */
class FormItemLocationHolder(itemView: View) : AbsFormItemLocationHolder(itemView) {
    private var mLocationExpand: LinearLayout = itemView.findViewById(R.id.item_form_location_expand)

    init {
        mLocationExpand.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 500
        )
        mLocationExpand.setBackgroundColor(Color.LTGRAY)
    }

    override fun selectLocation(position: Int, data: FormItemEntity, listener: OnTListener<Boolean>) {
        val value = FormValueEntity(6)
        value.value = FormValueOfLocation(locName = "测试地址${Random.nextInt(10000)}", locX = 134.00, locY = 32.00)
        data.formValue = value
        listener.back(true)
    }
}

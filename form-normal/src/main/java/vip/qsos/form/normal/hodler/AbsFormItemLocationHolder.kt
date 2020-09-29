package vip.qsos.form.normal.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_location.view.*
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.model.FormValueOfLocation

/**位置类型视图
 * @author : 华清松
 */
abstract class AbsFormItemLocationHolder(itemView: View) : AbsFormHolder(itemView) {

    abstract fun selectLocation(position: Int, data: FormItemEntity, listener: OnTListener<Boolean>)

    override fun setData(position: Int, data: FormItemEntity) {
        super.setData(position, data)
        itemView.item_form_location.hint = data.notice
        data.formValue?.value?.let {
            it as FormValueOfLocation
            itemView.item_form_location.text = it.locName
        }

        itemView.item_form_location.setOnClickListener {
            selectLocation(position, data, object : OnTListener<Boolean> {
                override fun back(t: Boolean) {
                    if (t) {
                        data.formValue?.value?.let {
                            it as FormValueOfLocation
                            itemView.item_form_location.text = it.locName
                        }
                    }
                }
            })
        }
    }

}

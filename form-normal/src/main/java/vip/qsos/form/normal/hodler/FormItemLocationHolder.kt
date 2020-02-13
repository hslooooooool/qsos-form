package vip.qsos.form.normal.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_location.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.model.FormValueOfLocation

/**
 * @author : 华清松
 *
 * 位置类型视图
 */
class FormItemLocationHolder(itemView: View) : BaseFormHolder<FormItemEntity<FormValueOfLocation>, FormValueOfLocation>(itemView) {

    override fun setData(position: Int, data: FormItemEntity<FormValueOfLocation>) {
        itemView.item_form_location.setOnClickListener {}

        data.formValue?.value?.let {
            itemView.item_form_location.text = it.locName
        }
    }

}

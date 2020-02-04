package vip.qsos.form_n.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_location.view.*
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_n.model.FormValueOfLocation

/**
 * @author : 华清松
 * 表单位置列表项视图
 */
class FormItemLocationHolder(itemView: View) : BaseFormHolder<FormItemEntity<FormValueOfLocation>, FormValueOfLocation>(itemView) {

    override fun setData(position: Int, data: FormItemEntity<FormValueOfLocation>) {
        itemView.item_form_location.setOnClickListener {}

        data.formValue?.value?.let {
            itemView.item_form_location.text = it.locName
        }
    }

}

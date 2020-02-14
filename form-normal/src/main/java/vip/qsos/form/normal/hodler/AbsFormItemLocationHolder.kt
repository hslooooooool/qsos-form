package vip.qsos.form.normal.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_location.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.model.FormValueOfLocation
import vip.qsos.lib.select.OnSelectListener

/**
 * @author : 华清松
 *
 * 位置类型视图
 */
abstract class AbsFormItemLocationHolder(itemView: View) : BaseFormHolder<FormItemEntity<FormValueOfLocation>, FormValueOfLocation>(itemView) {

    abstract fun selectLocation(position: Int, data: FormItemEntity<FormValueOfLocation>, listener: OnSelectListener<Boolean>)

    override fun setData(position: Int, formItem: FormItemEntity<FormValueOfLocation>) {
        formItem.formValue?.value?.let {
            itemView.item_form_location.text = it.locName
        }

        itemView.item_form_location.setOnClickListener {
            selectLocation(position, formItem, object : OnSelectListener<Boolean> {
                override fun select(data: Boolean) {
                    if (data) {
                        formItem.formValue?.value?.let {
                            itemView.item_form_location.text = it.locName
                        }
                    }
                }
            })
        }
    }

}

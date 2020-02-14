package vip.qsos.form.normal.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_text.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.model.FormValueOfText

/**
 * @author : 华清松
 *
 * 文本类型视图
 */
class FormItemTextHolder(itemView: View) : BaseFormHolder<FormItemEntity<FormValueOfText>, FormValueOfText>(itemView) {

    override fun setData(position: Int, formItem: FormItemEntity<FormValueOfText>) {
        formItem.formValue?.value?.let {
            itemView.item_form_text.text = it.content
        }
    }
}
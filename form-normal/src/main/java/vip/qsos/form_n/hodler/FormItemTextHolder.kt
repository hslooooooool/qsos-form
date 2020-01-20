package vip.qsos.form_n.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_text.view.*
import kotlinx.android.synthetic.main.form_normal_title.view.*
import vip.qsos.form_lib.base.BaseFormHolder
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_n.model.FormValueOfText
import vip.qsos.form_n.utils.FormValueUtil

/**
 * @author : 华清松
 * 表单文本列表项视图
 */
class FormItemTextHolder(itemView: View) : BaseFormHolder(itemView) {

    override fun setData(data: FormItemEntity, position: Int) {
        itemView.form_item_title.text = data.title

        itemView.form_item_title.setOnClickListener {
        }
        itemView.item_form_text.setOnClickListener {
        }

        FormValueUtil.getValue(data.formValue?.value, FormValueOfText::class.java)?.let {
            itemView.item_form_text.text = it.content
        }

    }

}

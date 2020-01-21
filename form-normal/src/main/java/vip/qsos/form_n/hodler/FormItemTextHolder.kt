package vip.qsos.form_n.hodler

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.form_item_text.view.*
import kotlinx.android.synthetic.main.form_normal_title.view.*
import vip.qsos.form_lib.base.BaseFormHolder
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_n.model.FormValueOfText

/**
 * @author : 华清松
 * 表单文本列表项视图
 */
class FormItemTextHolder(itemView: View) : BaseFormHolder<FormValueOfText>(itemView) {

    override fun setData(data: FormItemEntity<FormValueOfText>, position: Int) {
        itemView.form_item_title.text = data.title
        itemView.form_item_title.setOnClickListener {
            Toast.makeText(itemView.context, data.notice, Toast.LENGTH_SHORT).show()
        }

        data.formValue?.obj?.let {
            itemView.item_form_text.text = it.content
        }

    }

}

package vip.qsos.form_n.hodler

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.form_item_input.view.*
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_lib.model.FormValueEntity
import vip.qsos.form_n.model.FormValueOfText

/**
 * @author : 华清松
 * 输入文本类型视图
 */
class FormItemInputHolder(itemView: View) : BaseFormHolder<FormItemEntity<FormValueOfText>, FormValueOfText>(itemView) {

    override fun setData(position: Int, data: FormItemEntity<FormValueOfText>) {
        var text = data.formValue?.value
        if (text == null) {
            text = FormValueOfText("")
            val value = FormValueEntity<FormValueOfText>(
                    editable = true, position = 1
            )
            value.value = text
            data.formValue = value
        }
        itemView.item_form_input.setText(text.content)

        itemView.item_form_input.isEnabled = data.editable
        itemView.item_form_input.hint = data.notice ?: "点击输入"

        if (data.limitMax != null && data.limitMax!! > 0) {
            itemView.item_form_input.filters = arrayOf(InputFilter.LengthFilter(data.limitMax!!))
        }

        itemView.item_form_input.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable) {
                val content = itemView.item_form_input.text.toString()
                if (text.content != content) {
                    text.content = content
                    data.formValue!!.value = text
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }
}

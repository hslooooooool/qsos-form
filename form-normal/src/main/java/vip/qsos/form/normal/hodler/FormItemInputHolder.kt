package vip.qsos.form.normal.hodler

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.form_item_input.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.model.FormValueOfInput
import vip.qsos.form.normal.model.FormValueOfText

/**输入文本类型视图
 * @author : 华清松
 */
class FormItemInputHolder(itemView: View) : AbsFormHolder(itemView) {

    override fun setData(position: Int, data: FormItemEntity) {
        super.setData(position, data)
        val text: FormValueOfInput
        if (data.formValue == null) {
            text = FormValueOfInput("")
            val value = FormValueEntity(1, editable = true, position = 1)
            value.value = text
            data.formValue = value
        } else {
            text = data.formValue!!.value as FormValueOfInput
        }
        itemView.item_form_input.setText(text.content)

        itemView.item_form_input.isEnabled = data.editable
        itemView.item_form_input.hint = data.notice ?: "点击输入"

        if (data.limitMax > 0) {
            itemView.item_form_input.filters = arrayOf(InputFilter.LengthFilter(data.limitMax))
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

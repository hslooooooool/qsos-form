package vip.qsos.form_n.hodler

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.form_item_input.view.*
import kotlinx.android.synthetic.main.form_normal_title.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import vip.qsos.form_lib.base.BaseFormHolder
import vip.qsos.form_lib.model.FormDatabase
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_lib.model.FormValueEntity
import vip.qsos.form_n.model.FormValueOfText
import vip.qsos.form_n.utils.FormValueUtil
import vip.qsos.form_n.utils.db

/**
 * @author : 华清松
 * 表单文本列表项视图
 */
class FormItemInputHolder(itemView: View) : BaseFormHolder(itemView) {
    private val mJob = Job()

    override fun setData(data: FormItemEntity, position: Int) {
        itemView.form_item_title.text = data.title
        var text = FormValueUtil.getValue(data.formValue?.value, FormValueOfText::class.java)
        if (text == null) {
            text = FormValueOfText("")
            data.formValues!!.add(FormValueEntity(
                    formItemId = data.formId, editable = true, position = 1, value = text.toString()
            ))
        }
        itemView.item_form_input.setText(text.content)

        itemView.item_form_input.isEnabled = data.editable
        itemView.item_form_input.hint = data.notice ?: "点击输入"

        if (data.limitMax != null && data.limitMax!! > 0) {
            itemView.item_form_input.filters = arrayOf(InputFilter.LengthFilter(data.limitMax!!))
        }

        itemView.form_item_title.setOnClickListener {
            Toast.makeText(itemView.context, data.notice, Toast.LENGTH_LONG).show()
        }
        itemView.item_form_input.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable) {
                val content = itemView.item_form_input.text.toString()
                if (text.content != content) {
                    text.content = content
                    CoroutineScope(mJob).db<Long> {
                        db = { FormDatabase.INSTANCE!!.formValueDao.insert(data.formValue!!) }
                        onSuccess = {

                        }
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })

        itemView.form_item_title.setOnClickListener {
        }
    }
}

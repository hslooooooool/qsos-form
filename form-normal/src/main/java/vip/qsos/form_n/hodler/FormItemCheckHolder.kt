package vip.qsos.form_n.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_check.view.*
import kotlinx.android.synthetic.main.form_normal_title.view.*
import vip.qsos.form_lib.base.BaseFormHolder
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_n.model.FormValueOfCheck
import vip.qsos.form_n.utils.FormValueUtil

/**
 * @author : 华清松
 * 表单文本列表项视图
 */
class FormItemCheckHolder(itemView: View) : BaseFormHolder(itemView) {

    override fun setData(data: FormItemEntity, position: Int) {
        itemView.form_item_title.text = data.title
        itemView.form_item_check.text = getText(data)
        itemView.form_item_check.hint = data.notice

        itemView.form_item_title.setOnClickListener { }
        itemView.form_item_check.setOnClickListener {}
    }

    private fun getText(data: FormItemEntity): String {
        for (v in data.formValues!!) {
            if (v.getRealValue<FormValueOfCheck>() == null) {
                FormValueUtil.getValue(v.value, FormValueOfCheck::class.java)?.let {
                    v.setRealValue(it)
                }
            }
        }
        var text = ""
        when {
            data.formValues!!.size == 1 -> {
                text = data.formValues!![0].getRealValue<FormValueOfCheck>()?.ckName ?: ""
            }
            data.limitMax == 1 -> {
                for (v in data.formValues!!) {
                    val check = v.getRealValue<FormValueOfCheck>()!!
                    var checked = false
                    if (check.ckChecked) {
                        text = check.ckName ?: ""
                        checked = true
                    }
                    if (checked) break
                }
            }
            else -> {
                for (v in data.formValues!!) {
                    val check = v.getRealValue<FormValueOfCheck>()!!
                    if (check.ckChecked) {
                        text += check.ckName + ";"
                    }
                }
            }
        }
        return text
    }
}

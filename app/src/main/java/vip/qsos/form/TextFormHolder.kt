package vip.qsos.form

import android.annotation.SuppressLint
import android.view.View
import kotlinx.android.synthetic.main.item_form_item_text.view.*
import vip.qsos.form.base.BaseFormHolder
import vip.qsos.form.model.entity.FormItem

class TextFormHolder(itemView: View) : BaseFormHolder(itemView) {
    @SuppressLint("SetTextI18n")
    override fun setData(data: FormItem, position: Int) {
        itemView.item_form_item_title.text = "文本列表项$position"
    }
}
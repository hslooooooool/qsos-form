package vip.qsos.form

import android.annotation.SuppressLint
import android.view.View
import kotlinx.android.synthetic.main.item_form_item_file.view.*
import vip.qsos.form.base.BaseFormHolder
import vip.qsos.form.model.entity.FormItem

class FileFormHolder(itemView: View) : BaseFormHolder(itemView) {
    @SuppressLint("SetTextI18n")
    override fun setData(data: FormItem, position: Int) {
        itemView.item_form_item_image.text = "文件列表项$position"
    }
}
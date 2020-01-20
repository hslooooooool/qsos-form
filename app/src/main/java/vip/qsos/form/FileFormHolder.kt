package vip.qsos.form

import android.annotation.SuppressLint
import android.view.View
import kotlinx.android.synthetic.main.item_form_item_file.view.*
import vip.qsos.form_lib.base.BaseHolder
import vip.qsos.form_lib.model.FormItemEntity

class FileFormHolder(itemView: View) : BaseHolder<FormItemEntity>(itemView) {
    @SuppressLint("SetTextI18n")
    override fun setData(data: FormItemEntity, position: Int) {
        itemView.item_form_item_image.text = "文件列表项$position"
    }
}
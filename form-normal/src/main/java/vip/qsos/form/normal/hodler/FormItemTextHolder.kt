package vip.qsos.form.normal.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_text.view.*
import vip.qsos.form.lib.model.FormItemEntity

/**文本类型视图
 * @author : 华清松
 */
class FormItemTextHolder(itemView: View) : AbsFormHolder(itemView) {

    override fun setData(position: Int, data: FormItemEntity) {
        super.setData(position, data)
        data.formValue?.value?.let {
            itemView.item_form_text.text = it.content
        }
    }
}
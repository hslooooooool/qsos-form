package vip.qsos.form.normal.hodler

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.form_normal_title.view.*
import vip.qsos.form.lib.base.BaseFormHolder
import vip.qsos.form.lib.model.AbsValue
import vip.qsos.form.lib.model.FormItemEntity

/**表单列表项基础布局
 * @author : 华清松
 */
abstract class AbsFormHolder<I : FormItemEntity<T>, T : AbsValue>(itemView: View)
    : BaseFormHolder<I, T>(itemView) {

    override fun setData(position: Int, data: I) {
        itemView.form_item_title.text = data.title
        itemView.form_item_title.setOnClickListener {
            Toast.makeText(itemView.context, data.notice, Toast.LENGTH_SHORT).show()
        }
    }

}
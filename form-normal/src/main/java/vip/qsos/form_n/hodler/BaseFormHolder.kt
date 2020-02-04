package vip.qsos.form_n.hodler

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.form_normal_title.view.*
import vip.qsos.form_lib.base.AbsFormHolder
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_lib.model.FormValueType

/**
 * @author : 华清松
 * 表单列表项基础布局
 */
abstract class BaseFormHolder<I : FormItemEntity<T>, T : FormValueType>(itemView: View)
    : AbsFormHolder<I, T>(itemView) {

    override fun setData(data: I, position: Int) {
        itemView.form_item_title.text = data.title
        itemView.form_item_title.setOnClickListener {
            Toast.makeText(itemView.context, data.notice, Toast.LENGTH_SHORT).show()
        }
        setData(position, data)
    }

}
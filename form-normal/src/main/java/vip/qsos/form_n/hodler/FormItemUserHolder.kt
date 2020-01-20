package vip.qsos.form_n.hodler

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.form_item_user.view.*
import kotlinx.android.synthetic.main.form_normal_title.view.*
import vip.qsos.form_lib.base.BaseFormHolder
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_lib.model.FormValueEntity
import vip.qsos.form_n.adapter.FormUserAdapter

/**
 * @author : 华清松
 * 表单用户列表项视图
 */
class FormItemUserHolder(itemView: View) : BaseFormHolder(itemView) {

    @SuppressLint("SetTextI18n")
    override fun setData(data: FormItemEntity, position: Int) {
        val values: ArrayList<FormValueEntity> = data.formValues!!

        itemView.form_item_title.text = data.title
        itemView.item_form_users_size.text = "${values.size}\t人"

        if (itemView.item_form_users_rv.layoutManager == null) {
            itemView.item_form_users_rv.layoutManager = GridLayoutManager(itemView.context, 4)
            itemView.item_form_users_rv.adapter = FormUserAdapter(data.formValues!!)
        } else {
            itemView.item_form_users_rv.adapter!!.notifyDataSetChanged()
        }

        itemView.form_item_title.setOnClickListener {}
        itemView.item_form_users_size.setOnClickListener {}
    }

}

package vip.qsos.form.normal.hodler

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.form_item_user.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.adapter.FormUserAdapter
import vip.qsos.form.normal.model.FormValueOfUser
import vip.qsos.lib.select.OnSelectListener

/**
 * @author : 华清松
 *
 * 用户类型视图
 */
abstract class AbsFormItemUserHolder(
        itemView: View
) : BaseFormHolder<FormItemEntity<FormValueOfUser>, FormValueOfUser>(itemView) {

    abstract fun selectUser(users: ArrayList<FormValueEntity<FormValueOfUser>>, listener: OnSelectListener<Boolean>)

    @SuppressLint("SetTextI18n")
    override fun setData(position: Int, data: FormItemEntity<FormValueOfUser>) {

        itemView.rv_item_form_users.layoutManager = GridLayoutManager(itemView.context, 5)
        itemView.rv_item_form_users.adapter = FormUserAdapter(data.formValues!!)

        itemView.item_form_users_size.text = "${data.formValues!!.size}人"

        itemView.item_form_users_size.setOnClickListener {
            selectUser(data.formValues!!, object : OnSelectListener<Boolean> {
                override fun select(d: Boolean) {
                    if (d) {
                        itemView.rv_item_form_users.adapter?.notifyDataSetChanged()
                        itemView.item_form_users_size.text = "${data.formValues!!.size}人"
                    }
                }
            })
        }
    }
}
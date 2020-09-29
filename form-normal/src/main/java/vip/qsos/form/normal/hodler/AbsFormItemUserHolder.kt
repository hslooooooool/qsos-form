package vip.qsos.form.normal.hodler

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.form_item_user.view.*
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.adapter.FormItemUserItemHolder
import vip.qsos.form.normal.adapter.FormUserAdapter

/**用户类型视图
 * @author : 华清松
 */
abstract class AbsFormItemUserHolder(
        itemView: View
) : AbsFormHolder(itemView) {

    /**点击用户选择后方法调用*/
    abstract fun selectUser(data: FormItemEntity, listener: OnTListener<Boolean>)

    /**点击用户头像后方法调用*/
    abstract fun clickUser(position: Int, data: FormValueEntity)

    @SuppressLint("SetTextI18n")
    override fun setData(position: Int, data: FormItemEntity) {
        super.setData(position, data)
        itemView.rv_item_form_users.layoutManager = GridLayoutManager(itemView.context, 5)
        itemView.rv_item_form_users.adapter = FormUserAdapter(
                data.formValues,
                object : FormItemUserItemHolder.OnItemListener {
                    override fun item(position: Int, data: FormValueEntity) {
                        clickUser(position, data)
                    }
                },
                object : FormItemUserItemHolder.OnDeleteListener {
                    override fun delete(position: Int) {
                        itemView.rv_item_form_users.adapter?.notifyDataSetChanged()
                        itemView.item_form_users_size.text = "${data.formValues.size}人"
                    }
                }
        )

        itemView.item_form_users_size.text = "${data.formValues.size}人"

        itemView.item_form_users_size.setOnClickListener {
            selectUser(data, object : OnTListener<Boolean> {
                override fun back(t: Boolean) {
                    if (t) {
                        itemView.rv_item_form_users.adapter?.notifyDataSetChanged()
                        itemView.item_form_users_size.text = "${data.formValues.size}人"
                    }
                }
            })
        }
    }
}
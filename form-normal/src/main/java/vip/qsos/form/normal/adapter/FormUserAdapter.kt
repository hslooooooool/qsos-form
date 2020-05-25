package vip.qsos.form.normal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.form_item_user_item.view.*
import vip.qsos.form.lib.base.BaseHolder
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.R
import vip.qsos.form.normal.model.FormValueOfUser
import vip.qsos.form.normal.utils.GlideApp

/**用户列表容器
 * @author : 华清松
 */
class FormUserAdapter(
        var data: ArrayList<FormValueEntity<FormValueOfUser>>,
        private var mOnItemListener: FormItemUserItemHolder.OnItemListener,
        private var mOnDeleteListener: FormItemUserItemHolder.OnDeleteListener
) : RecyclerView.Adapter<BaseHolder<FormValueEntity<FormValueOfUser>>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<FormValueEntity<FormValueOfUser>> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_item_user_item, parent, false)
        return FormItemUserItemHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder<FormValueEntity<FormValueOfUser>>, position: Int) {
        holder as FormItemUserItemHolder
        holder.setData(position, data[position])
        holder.setDeleteListener(object : FormItemUserItemHolder.OnDeleteListener {
            override fun delete(position: Int) {
                data.remove(data[position])
                mOnDeleteListener.delete(position)
            }
        })
        holder.setOnItemListener(object : FormItemUserItemHolder.OnItemListener {
            override fun item(position: Int, data: FormValueEntity<FormValueOfUser>) {
                mOnItemListener.item(position, data)
            }
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

/**
 * @author : 华清松
 * 用户列表项布局
 */
class FormItemUserItemHolder(itemView: View) : BaseHolder<FormValueEntity<FormValueOfUser>>(itemView) {

    interface OnDeleteListener {
        fun delete(position: Int)
    }

    interface OnItemListener {
        fun item(position: Int, data: FormValueEntity<FormValueOfUser>)
    }

    private var mOnItemListener: OnItemListener? = null
    private var mOnDeleteListener: OnDeleteListener? = null

    fun setOnItemListener(listener: OnItemListener) {
        this.mOnItemListener = listener
    }

    fun setDeleteListener(listenerOn: OnDeleteListener) {
        this.mOnDeleteListener = listenerOn
    }

    override fun setData(position: Int, data: FormValueEntity<FormValueOfUser>) {
        data.value?.let { user ->
            itemView.tv_item_form_user_name.text = user.userName
            GlideApp.with(itemView.context)
                    .load(user.userAvatar)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .skipMemoryCache(false)
                    .into(itemView.iv_item_form_user_icon)

            itemView.iv_item_form_user_delete.visibility = if (data.editable) View.VISIBLE else View.GONE

            itemView.iv_item_form_user_icon.setOnClickListener {
                mOnItemListener?.item(position, data)
            }
            itemView.iv_item_form_user_delete.setOnClickListener {
                mOnDeleteListener?.delete(position)
            }
        }
    }
}


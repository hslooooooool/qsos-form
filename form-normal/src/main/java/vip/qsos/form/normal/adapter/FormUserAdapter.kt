package vip.qsos.form.normal.adapter

import android.content.Context
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

/**
 * @author : 华清松
 *
 * 用户列表容器
 */
class FormUserAdapter(
        var data: ArrayList<FormValueEntity<FormValueOfUser>>
) : RecyclerView.Adapter<BaseHolder<FormValueEntity<FormValueOfUser>>>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<FormValueEntity<FormValueOfUser>> {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_item_user_item, parent, false)
        return FormItemUserItemHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder<FormValueEntity<FormValueOfUser>>, position: Int) {
        holder as FormItemUserItemHolder
        holder.setData(data[position], position)
        holder.setDeleteListener(object : FormItemUserItemHolder.DeleteListener {
            override fun delete(position: Int) {
                data.remove(data[position])
                notifyDataSetChanged()
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

    interface DeleteListener {
        fun delete(position: Int)
    }

    private var mDeleteListener: DeleteListener? = null

    fun setDeleteListener(listener: DeleteListener) {
        this.mDeleteListener = listener
    }

    override fun setData(data: FormValueEntity<FormValueOfUser>, position: Int) {
        data.value?.let { user ->
            itemView.tv_item_form_user_name.text = user.userName
            GlideApp.with(itemView.context)
                    .load(user.userAvatar)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .skipMemoryCache(false)
                    .into(itemView.iv_item_form_user_icon)

            itemView.iv_item_form_user_delete.visibility = if (data.editable) View.VISIBLE else View.GONE

            itemView.iv_item_form_user_icon.setOnClickListener {
            }
            itemView.iv_item_form_user_delete.setOnClickListener {
                mDeleteListener?.delete(position)
            }
        }
    }
}


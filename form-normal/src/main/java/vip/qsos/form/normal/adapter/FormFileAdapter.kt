package vip.qsos.form.normal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.form_item_file_item.view.*
import vip.qsos.form.lib.base.BaseHolder
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.R
import vip.qsos.form.normal.model.FormValueOfFile
import vip.qsos.form.normal.utils.GlideApp

/**文件列表容器
 * @author : 华清松
 */
class FormFileAdapter(
        var data: ArrayList<FormValueEntity>,
        private var mOnItemListener: FormItemFileItemHolder.OnItemListener,
        private var mOnDeleteListener: FormItemFileItemHolder.OnDeleteListener
) : RecyclerView.Adapter<BaseHolder<FormValueEntity>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<FormValueEntity> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_item_file_item, parent, false)
        return FormItemFileItemHolder(view)
    }

    override fun onBindViewHolder(holder: BaseHolder<FormValueEntity>, position: Int) {
        holder as FormItemFileItemHolder
        holder.setData(position, data[position])
        holder.setDeleteListener(object : FormItemFileItemHolder.OnDeleteListener {
            override fun delete(position: Int) {
                data.remove(data[position])
                mOnDeleteListener.delete(position)
            }
        })
        holder.setOnItemListener(object : FormItemFileItemHolder.OnItemListener {
            override fun item(position: Int, data: FormValueEntity) {
                mOnItemListener.item(position, data)
            }
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

/**文件布局
 * @author : 华清松
 */
class FormItemFileItemHolder(itemView: View) : BaseHolder<FormValueEntity>(itemView) {

    interface OnDeleteListener {
        fun delete(position: Int)
    }

    interface OnItemListener {
        fun item(position: Int, data: FormValueEntity)
    }

    private var mOnItemListener: OnItemListener? = null
    private var mOnDeleteListener: OnDeleteListener? = null

    fun setOnItemListener(listener: OnItemListener) {
        this.mOnItemListener = listener
    }

    fun setDeleteListener(listenerOn: OnDeleteListener) {
        this.mOnDeleteListener = listenerOn
    }

    override fun setData(position: Int, data: FormValueEntity) {
        data.value.let { f ->
            val file = f as FormValueOfFile
            itemView.tv_item_form_file_name.text = file.fileName
            GlideApp.with(itemView.context)
                    .load(file.fileCover)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .skipMemoryCache(false)
                    .into(itemView.iv_item_form_file_icon)

            itemView.iv_item_form_file_delete.visibility = if (data.editable) View.VISIBLE else View.GONE

            itemView.iv_item_form_file_icon.setOnClickListener {
                mOnItemListener?.item(position, data)
            }
            itemView.iv_item_form_file_delete.setOnClickListener {
                mOnDeleteListener?.delete(position)
            }
        }
    }
}


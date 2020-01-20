package vip.qsos.form_lib.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form_lib.config.FormHelper
import vip.qsos.form_lib.model.FormItemEntity

/**
 * @author : 华清松
 * 表单列表项容器
 */
class FormAdapter constructor(
        var data: ArrayList<FormItemEntity>
) : RecyclerView.Adapter<BaseHolder<FormItemEntity>>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<FormItemEntity> {
        mContext = parent.context
        return FormHelper.getHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseHolder<FormItemEntity>, position: Int) {
        holder.setData(data[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return FormHelper.getViewType(data[position].valueType)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

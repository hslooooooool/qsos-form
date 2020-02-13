package vip.qsos.form.lib.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form.lib.config.FormHelper
import vip.qsos.form.lib.model.FormItemEntity

/**
 * @author : 华清松
 *
 * 表单列表项容器
 */
class FormAdapter constructor(
        var data: List<FormItemEntity<*>>
) : RecyclerView.Adapter<AbsFormHolder<FormItemEntity<*>, *>>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsFormHolder<FormItemEntity<*>, *> {
        mContext = parent.context
        return FormHelper.getHolder(parent, viewType) as AbsFormHolder<FormItemEntity<*>, *>
    }

    override fun onBindViewHolder(holder: AbsFormHolder<FormItemEntity<*>, *>, position: Int) {
        holder.setData(data[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return FormHelper.getViewType(data[position].valueType)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
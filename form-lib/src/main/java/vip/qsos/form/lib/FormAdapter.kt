package vip.qsos.form.lib

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form.lib.base.BaseFormHolder
import vip.qsos.form.lib.config.FormHelper
import vip.qsos.form.lib.model.FormItemEntity

/**表单列表项容器
 * @author : 华清松
 */
class FormAdapter constructor(
        var data: List<FormItemEntity<*>>
) : RecyclerView.Adapter<BaseFormHolder<FormItemEntity<*>, *>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseFormHolder<FormItemEntity<*>, *> {
        return FormHelper.getHolder(parent, viewType) as BaseFormHolder<FormItemEntity<*>, *>
    }

    override fun onBindViewHolder(holder: BaseFormHolder<FormItemEntity<*>, *>, position: Int) {
        holder.setData(position, data[position])
    }

    override fun getItemViewType(position: Int): Int {
        return FormHelper.getViewType(data[position].valueType)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
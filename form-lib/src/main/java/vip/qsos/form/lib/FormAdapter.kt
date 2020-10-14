package vip.qsos.form.lib

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form.lib.base.BaseFormHolder
import vip.qsos.form.lib.config.FormViewHelper
import vip.qsos.form.lib.model.FormItemEntity

/**表单列表项容器
 * @author : 华清松
 */
class FormAdapter constructor(
        var data: List<FormItemEntity>
) : RecyclerView.Adapter<BaseFormHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseFormHolder {
        return FormViewHelper.getHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseFormHolder, position: Int) {
        holder.setData(position, data[position])
    }

    override fun getItemViewType(position: Int): Int {
        return FormViewHelper.getViewType(data[position].valueType)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
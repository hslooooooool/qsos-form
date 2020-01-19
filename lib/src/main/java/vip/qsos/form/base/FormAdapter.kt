package vip.qsos.form.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form.config.FormHelper
import vip.qsos.form.model.entity.FormItem

/**
 * @author : 华清松
 * 表单列表项容器
 */
class FormAdapter constructor(var data: ArrayList<FormItem>) : RecyclerView.Adapter<BaseFormHolder>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseFormHolder {
        mContext = parent.context
        return FormHelper.getHolder( parent, viewType)
    }

    override fun onBindViewHolder(formHolder: BaseFormHolder, position: Int) {
        formHolder.setData(data[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return FormHelper.getViewType(data[position].valueType)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

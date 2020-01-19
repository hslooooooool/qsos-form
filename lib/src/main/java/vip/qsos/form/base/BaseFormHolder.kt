package vip.qsos.form.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form.model.entity.FormItem

/**
 * @author : 华清松
 * 表单列表项基础布局
 */
abstract class BaseFormHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**设置数据 */
    abstract fun setData(data: FormItem, position: Int)

}

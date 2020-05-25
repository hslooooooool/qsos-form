package vip.qsos.form.lib.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**列表项基础布局
 * @author : 华清松
 */
abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**设置数据 */
    abstract fun setData(position: Int, data: T)

}
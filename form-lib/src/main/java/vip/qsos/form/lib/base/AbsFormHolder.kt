package vip.qsos.form.lib.base

import android.view.View
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.AbsValue

/**
 * @author : 华清松
 *
 * 表单列表项基础布局
 */
abstract class AbsFormHolder<I : FormItemEntity<T>, T : AbsValue>(itemView: View)
    : BaseHolder<I>(itemView) {

    abstract fun setData(position: Int, formItem: I)

}
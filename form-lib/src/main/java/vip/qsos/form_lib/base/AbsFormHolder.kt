package vip.qsos.form_lib.base

import android.view.View
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_lib.model.FormValueType

/**
 * @author : 华清松
 * 表单列表项基础布局
 */
abstract class AbsFormHolder<I : FormItemEntity<T>, T : FormValueType>(itemView: View)
    : BaseHolder<I>(itemView) {

    abstract fun setData(position: Int, data: I)

}

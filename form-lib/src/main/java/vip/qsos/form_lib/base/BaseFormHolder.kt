package vip.qsos.form_lib.base

import android.view.View
import vip.qsos.form_lib.model.AbsFormValue
import vip.qsos.form_lib.model.FormItemEntity

/**
 * @author : 华清松
 * 表单列表项基础布局
 */
abstract class BaseFormHolder<T : AbsFormValue<T>>(itemView: View) : BaseHolder<FormItemEntity<T>>(itemView)

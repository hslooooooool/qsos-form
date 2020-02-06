package vip.qsos.form_lib.config

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import vip.qsos.form_lib.base.AbsFormHolder

/**
 * @author : 华清松
 * 表单功能配置清单
 */
interface FormConfig {

    /**获取ViewType
     * @param valueType 表单值类型->FormItemEntity.valueType
     * @see vip.qsos.form_lib.model.FormItemEntity.valueType
     * @return viewType
     * */
    fun getViewType(valueType: Int): Int

    /**获取表单值类型
     * @param viewType ViewType
     * @return ViewType
     * */
    fun getValueType(viewType: Int): Int

    /**获取列表项Holder
     * @param parent ViewGroup
     * @param viewType viewType
     * @return Any extends BaseHolder*/
    fun getHolder(parent: ViewGroup, viewType: Int): AbsFormHolder<*, *>

    /**获取列表项布局文件
     * @param valueType 表单值类型
     * @return layoutId
     * */
    @LayoutRes
    fun getLayoutId(valueType: Int): Int

}
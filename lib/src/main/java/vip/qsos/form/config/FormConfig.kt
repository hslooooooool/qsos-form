package vip.qsos.form.config

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import vip.qsos.form.base.BaseFormHolder

/**
 * @author : 华清松
 * 表单功能配置清单
 */
interface FormConfig {

    /**获取ViewType
     * @param valueType 表单值类型
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
     * @return Any extends BaseFormHolder*/
    fun getHolder(parent: ViewGroup, viewType: Int): BaseFormHolder

    /**获取列表项布局文件
     * @param valueType 表单值类型
     * @return layoutId
     * */
    @LayoutRes
    fun getLayoutId(valueType: Int): Int

}
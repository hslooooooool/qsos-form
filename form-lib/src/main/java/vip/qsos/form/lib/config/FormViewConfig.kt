package vip.qsos.form.lib.config

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import vip.qsos.form.lib.base.BaseFormHolder

/**表单功能配置接口
 * @author : 华清松
 */
interface FormViewConfig {

    /**获取ViewType
     * @param valueType 表单值类型->FormItemEntity.valueType
     * @see vip.qsos.form.lib.model.FormItemEntity.valueType
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
    fun getHolder(parent: ViewGroup, viewType: Int): BaseFormHolder

    /**获取列表项布局文件
     * @param valueType 表单值类型
     * @return layoutId
     * */
    @LayoutRes
    fun getLayoutId(valueType: Int): Int

}
package vip.qsos.form.lib.config

import android.view.ViewGroup
import vip.qsos.form.lib.base.BaseFormHolder

/**表单帮助类
 * @author : 华清松
 */
object FormHelper : FormConfig {

    private var formConfig: FormConfig? = null

    /**初始化*/
    fun init(config: FormConfig) {
        this.formConfig = config
    }

    /**获取代理对象*/
    private fun helper(): FormConfig {
        return formConfig
                ?: throw NullPointerException("请调用 FormHelper.init(config: FormConfig) 进行初始化!")
    }

    override fun getViewType(valueType: Int): Int {
        return helper().getViewType(valueType)
    }

    override fun getValueType(viewType: Int): Int {
        return helper().getValueType(viewType)
    }

    override fun getHolder(parent: ViewGroup, viewType: Int): BaseFormHolder {
        return helper().getHolder(parent, viewType)
    }

    override fun getLayoutId(valueType: Int): Int {
        return helper().getLayoutId(getViewType(valueType))
    }

}
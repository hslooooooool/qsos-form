package vip.qsos.form

import android.view.LayoutInflater
import android.view.ViewGroup
import vip.qsos.form.lib.config.FormConfig
import vip.qsos.form.normal.hodler.*

/**
 * @author : 华清松
 *
 * 表单配置
 */
class FormConfigImpl : FormConfig {

    override fun getViewType(valueType: Int): Int {
        return valueType
    }

    override fun getValueType(viewType: Int): Int {
        return viewType
    }

    override fun getHolder(parent: ViewGroup, viewType: Int): BaseFormHolder<*, *> {
        val layoutId = getLayoutId(getValueType(viewType))
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return when (viewType) {
            0 -> FormItemTextHolder(view)
            1 -> FormItemInputHolder(view)
            2 -> FormItemCheckHolder(view)
            3 -> FormItemTimeHolder(view)
            5 -> FormItemFileHolder(view)
            6 -> FormItemLocationHolder(view)
            else -> FormItemTextHolder(view)
        }
    }

    /** valueType = FormItemEntity.valueType */
    override fun getLayoutId(valueType: Int): Int {
        return when (getViewType(valueType)) {
            0 -> R.layout.form_item_text
            1 -> R.layout.form_item_input
            2 -> R.layout.form_item_check
            3 -> R.layout.form_item_time
            5 -> R.layout.form_item_file
            6 -> R.layout.form_item_location
            else -> R.layout.form_item_text
        }
    }

}
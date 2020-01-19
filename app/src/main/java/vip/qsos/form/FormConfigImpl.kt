package vip.qsos.form

import android.view.LayoutInflater
import android.view.ViewGroup
import vip.qsos.form.base.BaseFormHolder
import vip.qsos.form.config.FormConfig

class FormConfigImpl : FormConfig {

    override fun getViewType(valueType: Int): Int {
        return valueType
    }

    override fun getValueType(viewType: Int): Int {
        return viewType
    }

    override fun getHolder(parent: ViewGroup, viewType: Int): BaseFormHolder {
        val layoutId = getLayoutId(getValueType(viewType))
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return when (viewType) {
            1 -> TextFormHolder(view)
            2 -> TextFormHolder(view)
            3 -> FileFormHolder(view)
            else -> TextFormHolder(view)
        }
    }

    override fun getLayoutId(valueType: Int): Int {
        return when (getViewType(valueType)) {
            1 -> R.layout.item_form_item_text
            2 -> R.layout.item_form_item_text
            3 -> R.layout.item_form_item_file
            else -> R.layout.item_form_item_text
        }
    }

}
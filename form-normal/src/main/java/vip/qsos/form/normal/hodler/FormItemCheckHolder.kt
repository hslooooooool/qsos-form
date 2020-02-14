package vip.qsos.form.normal.hodler

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_item_check.view.*
import vip.qsos.form.normal.model.FormValueOfCheck
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.lib.select.OnSelectListener
import vip.qsos.lib.select.Operation
import vip.qsos.lib.select.SelectHelper

/**
 * @author : 华清松
 *
 * 选项类型视图
 */
class FormItemCheckHolder(itemView: View) : BaseFormHolder<FormItemEntity<FormValueOfCheck>, FormValueOfCheck>(itemView) {

    override fun setData(position: Int, formItem: FormItemEntity<FormValueOfCheck>) {
        itemView.form_item_check.text = getText(formItem)
        itemView.form_item_check.hint = formItem.notice
        itemView.form_item_check.isEnabled = formItem.editable

        itemView.form_item_check.setOnClickListener {
            showCheck(formItem, object : OnTListener<String?> {
                override fun back(t: String?) {
                    t?.let { itemView.form_item_check.text = it }
                }
            })
        }
    }

    /**获取所选项展示*/
    private fun getText(data: FormItemEntity<FormValueOfCheck>): String {
        var text = ""
        when {
            data.formValues!!.size == 1 -> {
                text = data.formValue!!.value?.ckName ?: ""
            }
            data.formValues!!.isNotEmpty() && data.limitMax == 1 -> {
                for (v in data.formValues!!) {
                    val check = v.value
                    var checked = false
                    if (check?.ckChecked == true) {
                        text = check.ckName
                        checked = true
                    }
                    if (checked) break
                }
            }
            data.formValues!!.isNotEmpty() -> {
                for (v in data.formValues!!) {
                    val check = v.value
                    if (check?.ckChecked == true) {
                        text += check.ckName + ";"
                    }
                }
            }
            else -> text = ""
        }
        return text
    }

    /**选择弹窗*/
    private fun showCheck(data: FormItemEntity<FormValueOfCheck>, listener: OnTListener<String?>) {
        if (data.editable) {
            /**检查选项合法性*/
            var checkValue = true
            data.formValues!!.forEach {
                val check = it.value
                if (check?.ckName == null || TextUtils.isEmpty(check.ckName)) {
                    checkValue = false
                }
            }
            if (!checkValue) {
                Toast.makeText(itemView.context, "表单数据不合法,无法交互", Toast.LENGTH_LONG).show()
                return
            }
            if (data.limitMax == 1) {
                showSingleCheck(data, listener)
            } else {
                showMultiCheck(data, listener)
            }
        }
    }

    /**单选弹窗*/
    private fun showSingleCheck(formItemEntity: FormItemEntity<FormValueOfCheck>, listener: OnTListener<String?>) {
        val mOperations = arrayListOf<Operation>()
        formItemEntity.formValues!!.forEach {
            mOperations.add(Operation(it.value!!.ckName, it.value!!.ckValue, it.value!!.ckChecked, it.editable))
        }
        SelectHelper.selectOfSingle(
                activity = itemView.context as AppCompatActivity,
                operations = mOperations,
                listener = object : OnSelectListener<Operation> {
                    override fun select(data: Operation) {
                        var name: String? = null
                        formItemEntity.formValues!!.forEach { entity ->
                            val realValue = entity.value!!
                            realValue.ckChecked = entity.value!!.ckName == data.key
                            if (realValue.ckChecked) name = realValue.ckName
                            entity.value = realValue
                        }
                        listener.back(name)
                    }
                })
    }

    /**多选弹窗*/
    private fun showMultiCheck(formItemEntity: FormItemEntity<FormValueOfCheck>, listener: OnTListener<String?>) {
        val mOperations = arrayListOf<Operation>()
        formItemEntity.formValues!!.forEach {
            mOperations.add(Operation(it.value!!.ckName, it.value!!.ckValue, it.value!!.ckChecked, it.editable))
        }

        SelectHelper.selectOfMultiple(
                activity = itemView.context as AppCompatActivity,
                title = formItemEntity.title,
                limitMax = formItemEntity.limitMax ?: 0,
                operations = mOperations,
                listener = object : OnSelectListener<List<Operation>> {
                    override fun select(data: List<Operation>) {
                        data.forEach { o ->
                            formItemEntity.formValues!!.find {
                                it.value!!.ckName == o.key
                            }.also {
                                it?.value!!.ckChecked = o.checked
                            }
                        }
                        listener.back(getText(formItemEntity))
                    }
                })
    }

}

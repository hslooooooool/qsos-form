package vip.qsos.form.normal.hodler

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_item_check.view.*
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.model.FormValueOfCheck
import vip.qsos.lib.select.OnSelectListener
import vip.qsos.lib.select.Operation
import vip.qsos.lib.select.SelectHelper

/**选项类型视图
 * @author : 华清松
 */
class FormItemCheckHolder(itemView: View) : AbsFormHolder(itemView) {

    override fun setData(position: Int, data: FormItemEntity) {
        super.setData(position, data)
        itemView.form_item_check.text = getText(data)
        itemView.form_item_check.hint = data.notice
        itemView.form_item_check.isEnabled = data.editable

        itemView.form_item_check.setOnClickListener {
            showCheck(data, object : OnTListener<String?> {
                override fun back(t: String?) {
                    t?.let { itemView.form_item_check.text = it }
                }
            })
        }
    }

    /**获取所选项展示*/
    private fun getText(data: FormItemEntity): String {
        var text = ""
        when {
            data.formValues.size == 1 -> {
                val v = data.formValue?.value as FormValueOfCheck
                text = v.ckName
            }
            data.formValues.isNotEmpty() && data.limitMax == 1 -> {
                for (value in data.formValues) {
                    val check = value.value as FormValueOfCheck
                    var checked = false
                    if (check.ckChecked) {
                        text = check.ckName
                        checked = true
                    }
                    if (checked) break
                }
            }
            data.formValues.isNotEmpty() -> {
                for (v in data.formValues) {
                    val check = v.value as FormValueOfCheck
                    if (check.ckChecked) {
                        text += check.ckName + ";"
                    }
                }
            }
            else -> text = ""
        }
        return text
    }

    /**选择弹窗*/
    private fun showCheck(data: FormItemEntity, listener: OnTListener<String?>) {
        if (data.editable) {
            /**检查选项合法性*/
            var checkValue = true
            data.formValues.forEach {
                val check = it.value as FormValueOfCheck
                if (TextUtils.isEmpty(check.ckName)) {
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
    private fun showSingleCheck(formItemEntity: FormItemEntity, listener: OnTListener<String?>) {
        val mOperations = arrayListOf<Operation>()
        formItemEntity.formValues.forEach {
            val v = it.value as FormValueOfCheck
            mOperations.add(Operation(v.ckName, v.ckValue, v.ckChecked, it.editable))
        }
        SelectHelper.selectOfSingle(
                activity = itemView.context as AppCompatActivity,
                operations = mOperations,
                listener = object : OnSelectListener<Operation> {
                    override fun select(data: Operation) {
                        var name: String? = null
                        formItemEntity.formValues.forEach { entity ->
                            val realValue = entity.value as FormValueOfCheck
                            realValue.ckChecked = realValue.ckName == data.key
                            if (realValue.ckChecked) name = realValue.ckName
                            entity.value = realValue
                        }
                        listener.back(name)
                    }
                })
    }

    /**多选弹窗*/
    private fun showMultiCheck(formItemEntity: FormItemEntity, listener: OnTListener<String?>) {
        val mOperations = arrayListOf<Operation>()
        formItemEntity.formValues.forEach {
            val v = it.value as FormValueOfCheck
            mOperations.add(Operation(v.ckName, v.ckValue, v.ckChecked, it.editable))
        }

        SelectHelper.selectOfMultiple(
                activity = itemView.context as AppCompatActivity,
                title = formItemEntity.title,
                limitMax = formItemEntity.limitMax ?: 0,
                operations = mOperations,
                listener = object : OnSelectListener<List<Operation>> {
                    override fun select(data: List<Operation>) {
                        data.forEach { o ->
                            formItemEntity.formValues.find {
                                val v = it.value as FormValueOfCheck
                                v.ckName == o.key
                            }.also {
                                val v = it?.value as FormValueOfCheck?
                                v?.ckChecked = o.checked
                            }
                        }
                        listener.back(getText(formItemEntity))
                    }
                })
    }

}

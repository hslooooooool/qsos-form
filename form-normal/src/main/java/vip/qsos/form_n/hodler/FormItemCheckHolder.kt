package vip.qsos.form_n.hodler

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.form_item_check.view.*
import kotlinx.android.synthetic.main.form_normal_title.view.*
import vip.qsos.form_lib.base.BaseFormHolder
import vip.qsos.form_lib.callback.OnTListener
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_n.model.FormValueOfCheck
import vip.qsos.form_n.utils.FormValueUtil
import vip.qsos.form_n.widget.dialog.BottomDialogUtils
import vip.qsos.form_n.widget.dialog.Operation

/**
 * @author : 华清松
 * 表单列表项选项类型视图
 */
class FormItemCheckHolder(itemView: View) : BaseFormHolder(itemView) {

    override fun setData(data: FormItemEntity, position: Int) {
        itemView.form_item_title.text = data.title
        itemView.form_item_check.text = getText(data)
        itemView.form_item_check.hint = data.notice

        itemView.form_item_title.setOnClickListener {
            Toast.makeText(itemView.context, data.notice, Toast.LENGTH_SHORT).show()
        }
        if (data.editable) {
            itemView.form_item_check.setOnClickListener {
                showCheck(data, object : OnTListener<String?> {
                    override fun back(t: String?) {
                        t?.let { itemView.form_item_check.text = it }
                    }
                })
            }
        } else {
            itemView.form_item_check.setOnClickListener(null)
        }
    }

    private fun getText(data: FormItemEntity): String {
        for (v in data.formValues!!) {
            if (v.getRealValue<FormValueOfCheck>() == null) {
                FormValueUtil.getValue(v.value, FormValueOfCheck::class.java)?.let {
                    v.setRealValue(it)
                }
            }
        }
        var text = ""
        when {
            data.formValues!!.size == 1 -> {
                text = data.formValues!![0].getRealValue<FormValueOfCheck>()?.ckName ?: ""
            }
            data.limitMax == 1 -> {
                for (v in data.formValues!!) {
                    val check = v.getRealValue<FormValueOfCheck>()!!
                    var checked = false
                    if (check.ckChecked) {
                        text = check.ckName ?: ""
                        checked = true
                    }
                    if (checked) break
                }
            }
            else -> {
                for (v in data.formValues!!) {
                    val check = v.getRealValue<FormValueOfCheck>()!!
                    if (check.ckChecked) {
                        text += check.ckName + ";"
                    }
                }
            }
        }
        return text
    }

    private fun showCheck(data: FormItemEntity, listener: OnTListener<String?>) {
        if (data.editable) {
            if (data.limitMax == 1) {
                showSingleCheck(data, listener)
            } else {
                showMultiCheck(data, listener)
            }
        }
    }

    private fun showSingleCheck(data: FormItemEntity, listener: OnTListener<String?>) {
        val names = arrayListOf<String>()
        var checkIndex = 0
        data.formValues!!.forEachIndexed { index, formValueEntity ->
            val check = formValueEntity.getRealValue<FormValueOfCheck>()
            names.add(check!!.ckName!!)
            if (check.ckChecked) checkIndex = index
        }
        val items = names.toTypedArray()
        AlertDialog.Builder(itemView.context).run {
            setTitle(data.title)
            setSingleChoiceItems(items, checkIndex) { dialog, which ->
                var name: String? = null
                data.formValues!!.forEachIndexed { index, entity ->
                    val realValue = entity.getRealValue<FormValueOfCheck>()!!
                    if (index == which) name = realValue.ckName
                    realValue.ckChecked = index == which
                    entity.value = realValue.toString()
                }
                dialog.dismiss()
                listener.back(name)
            }
            create()
        }.also {
            it.show()
        }
    }

    private fun showMultiCheck(data: FormItemEntity, listener: OnTListener<String?>) {
        val names = linkedMapOf<String, Int>()
        val operations = arrayListOf<Operation>()
        val limitMax = data.limitMax ?: 0
        /**检查选项合法性*/
        var checkValue = true
        data.formValues!!.forEachIndexed { index, formValueEntity ->
            val check = formValueEntity.getRealValue<FormValueOfCheck>()
            if (check == null) {
                checkValue = false
            } else {
                val name = check.ckName!!
                names[name] = index
                operations.add(Operation(key = name, check = check.ckChecked))
            }
        }
        if (!checkValue) {
            Toast.makeText(itemView.context, "表单数据不合法,无法交互", Toast.LENGTH_LONG).show()
            return
        }

        BottomDialogUtils.showMultiDialog(
                itemView.context,
                data.title, limitMax, operations,
                object : OnTListener<List<Operation>> {
                    override fun back(t: List<Operation>) {
                        t.forEach {
                            val value = data.formValues!![names[it.key!!]!!]
                            val realValue = value.getRealValue<FormValueOfCheck>()!!
                            realValue.ckChecked = it.isCheck
                            value.value = realValue.toString()
                        }
                        listener.back(getText(data))
                    }
                })
    }

}

package vip.qsos.form.holder

import android.view.View
import android.widget.Toast
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.hodler.AbsFormItemFileHolder
import vip.qsos.form.normal.model.FormValueOfFile
import vip.qsos.lib.select.OnSelectListener

/**
 * @author : 华清松
 *
 * 文件类型视图
 */
class FormItemFileHolder(
        itemView: View
) : AbsFormItemFileHolder(itemView) {

    override fun takeFile(type: FormValueOfFile.Type, data: FormItemEntity<FormValueOfFile>, listener: OnSelectListener<Boolean>) {
        val size = data.formValues!!.size
        val limitMax = data.limitMax ?: 0
        val value = FormValueEntity<FormValueOfFile>()
        value.value = FormValueOfFile(fileName = "${type.name}${size + 1}", fileUrl = "http://www.qsos.vip/upload/2018/11/ic_launcher20181225044818498.png")
        if (limitMax > 0 && size < limitMax) {
            data.formValues!!.add(value)
            listener.select(true)
        } else {
            listener.select(false)
        }
    }

    override fun clickFile(position: Int, data: FormValueEntity<FormValueOfFile>) {
        Toast.makeText(itemView.context, data.value?.fileName, Toast.LENGTH_SHORT).show()
    }

}
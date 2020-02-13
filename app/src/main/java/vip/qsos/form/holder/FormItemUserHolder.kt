package vip.qsos.form.holder

import android.view.View
import android.widget.Toast
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.hodler.AbsFormItemUserHolder
import vip.qsos.form.normal.model.FormValueOfUser
import vip.qsos.lib.select.OnSelectListener

/**
 * @author : 华清松
 *
 * 用户类型视图
 */
class FormItemUserHolder(
        itemView: View
) : AbsFormItemUserHolder(itemView) {

    override fun selectUser(data: FormItemEntity<FormValueOfUser>, listener: OnSelectListener<Boolean>) {
        val size = data.formValues!!.size
        val limitMax = data.limitMax ?: 0
        val value = FormValueEntity<FormValueOfUser>()
        value.value = FormValueOfUser(userName = "抄送人员${size + 1}", userAvatar = "http://www.qsos.vip/upload/2018/11/ic_launcher20181225044818498.png")
        if (limitMax > 0) {
            if (size < limitMax) {
                data.formValues!!.add(value)
                listener.select(true)
            } else {
                listener.select(false)
            }
        } else {
            data.formValues!!.add(value)
            listener.select(true)
        }
    }

    override fun clickUser(position: Int, data: FormValueEntity<FormValueOfUser>) {
        Toast.makeText(itemView.context, data.value?.userName, Toast.LENGTH_SHORT).show()
    }

}
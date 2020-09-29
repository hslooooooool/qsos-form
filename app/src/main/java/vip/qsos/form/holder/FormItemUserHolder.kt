package vip.qsos.form.holder

import android.view.View
import android.widget.Toast
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.lib.model.FormValueEntity
import vip.qsos.form.normal.hodler.AbsFormItemUserHolder
import vip.qsos.form.normal.model.FormValueOfUser

/**用户类型视图
 * @author : 华清松
 */
class FormItemUserHolder(
        itemView: View
) : AbsFormItemUserHolder(itemView) {

    override fun selectUser(data: FormItemEntity, listener: OnTListener<Boolean>) {
        val size = data.formValues.size
        val limitMax = data.limitMax
        val value = FormValueEntity(4)
        value.value = FormValueOfUser(userName = "抄送人员${size + 1}", userAvatar = "http://www.qsos.vip/upload/2018/11/ic_launcher20181225044818498.png")
        if (limitMax > 0) {
            if (size < limitMax) {
                data.formValues.add(value)
                listener.back(true)
            } else {
                listener.back(false)
            }
        } else {
            data.formValues.add(value)
            listener.back(true)
        }
    }

    override fun clickUser(position: Int, data: FormValueEntity) {
        val v = data.value as FormValueOfUser
        Toast.makeText(itemView.context, v.userName, Toast.LENGTH_SHORT).show()
    }

}
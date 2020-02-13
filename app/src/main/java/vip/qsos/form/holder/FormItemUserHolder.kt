package vip.qsos.form.holder

import android.view.View
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

    override fun selectUser(users: ArrayList<FormValueEntity<FormValueOfUser>>, listener: OnSelectListener<Boolean>) {
        val value = FormValueEntity<FormValueOfUser>()
        value.value = FormValueOfUser(userName = "抄送人员${users.size + 1}", userAvatar = "http://www.qsos.vip/upload/2018/11/ic_launcher20181225044818498.png")
        users.add(value)
        listener.select(true)
    }
}
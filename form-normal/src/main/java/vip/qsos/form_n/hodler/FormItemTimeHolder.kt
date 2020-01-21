package vip.qsos.form_n.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_time.view.*
import kotlinx.android.synthetic.main.form_normal_title.view.*
import vip.qsos.form_lib.base.BaseFormHolder
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_n.model.FormValueOfTime
import vip.qsos.form_n.utils.DateUtils

/**
 * @author : 华清松
 * 表单时间列表项视图
 */
class FormItemTimeHolder(itemView: View) : BaseFormHolder<FormValueOfTime>(itemView) {

    override fun setData(data: FormItemEntity<FormValueOfTime>, position: Int) {
        itemView.form_item_title.text = data.title
        itemView.item_form_time.hint = data.notice
        itemView.item_form_time.text = getTime(data)

        itemView.form_item_title.setOnClickListener {}
        itemView.item_form_time.setOnClickListener {}
    }

    private fun getTime(data: FormItemEntity<FormValueOfTime>): String {
        var time = ""
        if (data.formValues != null) {
            if (data.formValues!!.size == 1) {
                data.formValue?.obj?.let {
                    if (it.timeStart > 0L) {
                        time = DateUtils.format(millis = it.timeStart, pattern = data.formValue?.limit)
                    }
                }

            } else if (data.formValues!!.size == 2) {
                val time1 = data.formValues!![0].obj
                val time2 = data.formValues!![1].obj
                if (time1!!.timeStart > 0L && time2!!.timeStart > 0L) {
                    time = DateUtils.format(millis = time1.timeStart, pattern = data.formValues!![0].limit) +
                            "\t至\t" +
                            DateUtils.format(millis = time2.timeStart, pattern = data.formValues!![1].limit)
                }
            }
        }
        return time
    }
}
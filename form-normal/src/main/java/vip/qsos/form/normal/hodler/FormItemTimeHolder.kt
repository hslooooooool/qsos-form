package vip.qsos.form.normal.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_time.view.*
import vip.qsos.form.lib.model.FormItemEntity
import vip.qsos.form.normal.model.FormValueOfTime
import vip.qsos.form.normal.utils.DateUtils
import vip.qsos.timepicker.CustomDatePicker
import vip.qsos.timepicker.OnDateListener
import vip.qsos.timepicker.TimePickHelper
import java.util.*

/**时间类型视图
 * @author : 华清松
 */
class FormItemTimeHolder(itemView: View) : AbsFormHolder(itemView) {

    override fun setData(position: Int, data: FormItemEntity) {
        super.setData(position, data)
        itemView.item_form_time.hint = data.notice
        itemView.item_form_time.text = getTime(data)

        itemView.item_form_time.isEnabled = data.editable
        data.formValue?.value?.let { time ->
            val v = time as FormValueOfTime
            itemView.item_form_time.setOnClickListener {
                TimePickHelper.picker(
                        context = itemView.context, timeType = CustomDatePicker.Type.YMDHM,
                        selected = Date(time.time),
                        limitMin = if (time.timeLimitMin > 0) Date(time.timeLimitMin) else null,
                        limitMax = if (time.timeLimitMax > 0) Date(time.timeLimitMax) else null,
                        onDateListener = object : OnDateListener {
                            override fun setDate(date: Date?) {
                                date?.let {
                                    v.time = date.time
                                    itemView.item_form_time.text = getTime(data)
                                }
                            }
                        }
                )
            }
        }
    }

    private fun getTime(data: FormItemEntity): String {
        var time = ""
        if (data.formValues.size == 1) {
            data.formValue?.value?.let {
                it as FormValueOfTime
                if (it.time > 0L) {
                    time = DateUtils.format(millis = it.time, pattern = data.formValue?.limitFormat)
                }
            }
        } else if (data.formValues.size == 2) {
            val time1 = data.formValues[0].value as FormValueOfTime
            val time2 = data.formValues[1].value as FormValueOfTime
            if (time1.time > 0L && time2.time > 0L) {
                time = DateUtils.format(millis = time1.time, pattern = data.formValues[0].limitFormat) +
                        "\t至\t" +
                        DateUtils.format(millis = time2.time, pattern = data.formValues[1].limitFormat)
            }
        }
        return time
    }
}

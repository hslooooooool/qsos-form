package vip.qsos.form_n.hodler

import android.view.View
import kotlinx.android.synthetic.main.form_item_time.view.*
import vip.qsos.form_lib.model.FormItemEntity
import vip.qsos.form_n.model.FormValueOfTime
import vip.qsos.form_n.utils.DateUtils
import vip.qsos.form_n.widget.dialog.BottomDialogUtils
import vip.qsos.form_n.widget.dialog.OnDateListener
import java.util.*

/**
 * @author : 华清松
 * 时间类型视图
 */
class FormItemTimeHolder(itemView: View) : BaseFormHolder<FormItemEntity<FormValueOfTime>, FormValueOfTime>(itemView) {

    override fun setData(position: Int, data: FormItemEntity<FormValueOfTime>) {
        itemView.item_form_time.hint = data.notice
        itemView.item_form_time.text = getTime(data)

        if (data.editable) {
            data.formValue?.value?.let { time ->
                itemView.item_form_time.setOnClickListener {
                    BottomDialogUtils.selectDate(
                            context = itemView.context,
                            limitMin = if (time.timeLimitMin != null) Date(time.timeLimitMin!!) else null,
                            limitMax = if (time.timeLimitMax != null) Date(time.timeLimitMax!!) else null,
                            selected = Date(time.timeStart),
                            showDayTime = true,
                            showSpecificTime = true,
                            onDateListener = object : OnDateListener {
                                override fun setDate(type: Int?, date: Date?) {
                                    date?.let {
                                        data.formValue!!.value!!.timeStart = date.time
                                        itemView.item_form_time.text = getTime(data)
                                    }
                                }
                            }
                    )
                }
            }
        }
    }

    private fun getTime(data: FormItemEntity<FormValueOfTime>): String {
        var time = ""
        if (data.formValues != null) {
            if (data.formValues!!.size == 1) {
                data.formValue?.value?.let {
                    if (it.timeStart > 0L) {
                        time = DateUtils.format(millis = it.timeStart, pattern = data.formValue?.limit)
                    }
                }

            } else if (data.formValues!!.size == 2) {
                val time1 = data.formValues!![0].value
                val time2 = data.formValues!![1].value
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

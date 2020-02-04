package vip.qsos.form_n.widget.dialog

import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form_lib.callback.OnTListener
import vip.qsos.form_n.R
import vip.qsos.form_n.widget.FormMaxHeightRecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

/**
 * @author 华清松
 * 底部自定义弹窗
 */
object BottomDialogUtils {

    /**
     * 具体时间选择
     *
     * @param context
     * @param showSpecificTime 是否显示时分
     * @param showDayTime      是否显示日
     * @param limitMin 最小可选时间
     * @param limitMax 最大可选时间
     * @param selected 默认选择的时间
     * @param onDateListener 选择结果回调
     */
    fun selectDate(
            context: Context,
            limitMin: Date? = null, limitMax: Date? = null, selected: Date? = null,
            showDayTime: Boolean = true, showSpecificTime: Boolean = true,
            onDateListener: OnDateListener
    ) {
        var selectDate = selected
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
        // 设置可选年范围
        val span = 3L
        // 获取当前可选时间
        val nowDate = Date()
        /*可选范围*/
        val sDate: Date
        val eDate: Date
        /*将要设置启动时间，那么启动时间不得大于完结时间，如果完结时间为空，则随意设置，但不得小于当前时间，但如果开始时间不为空，则判断开始与当前时间大小*/
        if (limitMax == null) {
            sDate = limitMin ?: Date(nowDate.time - span * 365 * 60 * 24 * 1000 * 60L)
            eDate = Date(sDate.time + (if (limitMin == null) 2 else 1) * span * 365 * 60 * 24 * 1000 * 60L)
        } else {
            /*完结时间不为空，开始时间不得大于完结时间，则最大可设置时间不得大于完结时间*/
            eDate = Date(limitMax.time)
            sDate = limitMin ?: Date(eDate.time - span * 365 * 60 * 24 * 1000 * 60L)
        }
        /*构建时间选择窗口*/
        val customDatePicker = CustomDatePicker(context, sdf, sdf.format(sDate), sdf.format(eDate), onDateListener)

        if (selectDate == null || selectDate.time > eDate.time || selectDate.time < sDate.time && abs(selectDate.time - sDate.time) > 1000) {
            selectDate = nowDate
        }
        customDatePicker.showDayTime(showDayTime, showSpecificTime)
        customDatePicker.setIsLoop(false)
        customDatePicker.show(sdf.format(selectDate))
    }

    /**底部弹窗单项选择*/
    fun setBottomChoseListView(
            context: Context,
            operations: List<Operation>,
            onTListener: OnTListener<Operation>?
    ) {
        val bottomDialog = BottomDialog()
        bottomDialog.setFragmentManager((context as AppCompatActivity).supportFragmentManager)
        bottomDialog.setLayoutRes(R.layout.form_operation_rv)
        bottomDialog.setDimAmount(0.6f)
        bottomDialog.setViewListener(object : BottomDialog.ViewListener {
            override fun bindView(dialog: BaseBottomDialog) {
                val operationRv = dialog.findViewById<FormMaxHeightRecyclerView>(R.id.operation_rv)
                val operationClose = dialog.findViewById<TextView>(R.id.operation_close)
                operationRv.layoutManager = LinearLayoutManager(context)
                val operationAdapter = OperationAdapter(context, operations)
                operationRv.adapter = operationAdapter

                operationAdapter.setOnItemClickListener(object : OnTListener<Operation> {
                    override fun back(t: Operation) {
                        if (onTListener != null) {
                            onTListener.back(t)
                            bottomDialog.dismiss()
                        }
                        if (bottomDialog.isVisible) {
                            bottomDialog.dismiss()
                        }
                    }
                })

                operationClose.setOnClickListener {
                    if (bottomDialog.isVisible) {
                        bottomDialog.dismiss()
                    }
                }

            }
        })
        bottomDialog.show()
    }

    /**底部弹窗多选*/
    fun showMultiDialog(
            context: Context,
            title: String?, limitMax: Int,
            operations: List<Operation>,
            onTListener: OnTListener<List<Operation>>?
    ) {
        val bottomDialog = BottomDialog()
        bottomDialog.setFragmentManager((context as AppCompatActivity).supportFragmentManager)
        bottomDialog.setLayoutRes(R.layout.form_quote_selete)
        bottomDialog.setDimAmount(0.6f)
        bottomDialog.setViewListener(object : BottomDialog.ViewListener {
            override fun bindView(dialog: BaseBottomDialog) {
                val screen = dialog.findViewById<RecyclerView>(R.id.rv_select) // 可选列表
                val tvTitle = dialog.findViewById<TextView>(R.id.tv_select_title)// 标题
                val tvCancel = dialog.findViewById<TextView>(R.id.tv_select_cancel) // 取消按钮
                val tvSure = dialog.findViewById<TextView>(R.id.tv_select_sure) // 确认按钮
                var checkedNum = 0
                operations.forEach {
                    if (it.isCheck) checkedNum++
                }
                val adapter = ConditionChoseAdapter(context, operations, checkedNum, limitMax)
                screen.layoutManager = LinearLayoutManager(context)
                screen.adapter = adapter
                tvTitle.text = "$title"
                tvCancel.setOnClickListener {
                    if (bottomDialog.isVisible) {
                        bottomDialog.dismiss()
                    }
                }
                tvSure.setOnClickListener {
                    onTListener?.back(adapter.data)
                    if (bottomDialog.isVisible) {
                        bottomDialog.dismiss()
                    }
                }
            }
        })
        bottomDialog.show()
    }

}
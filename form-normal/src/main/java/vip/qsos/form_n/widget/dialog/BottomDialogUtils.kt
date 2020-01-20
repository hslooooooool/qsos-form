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

    var imageOperation: MutableList<Operation> = ArrayList()
    var fileOperation: MutableList<Operation> = ArrayList()
    var headOperation: MutableList<Operation> = ArrayList()
    var sexOperation: MutableList<Operation> = ArrayList()
    var commonOperation: MutableList<Operation> = ArrayList()
    var CommonPhrases: MutableList<Operation> = ArrayList()

    init {
        fileOperation.add(Operation(R.drawable.form_take_photo, "拍照", 0, false, "CAMERA"))
        fileOperation.add(Operation(R.drawable.form_take_image, "图库", 1, false, "ALBUM"))
        fileOperation.add(Operation(R.drawable.form_take_video, "录制视频", 2, false, "VIDEO"))
        fileOperation.add(Operation(R.drawable.form_take_audio, "录制音频", 3, false, "AUDIO"))
        fileOperation.add(Operation(R.drawable.form_take_file, "文件", 3, false, "FILE"))

        headOperation.add(Operation(R.drawable.form_take_photo, "拍照", 0, false, "CAMERA"))
        headOperation.add(Operation(R.drawable.form_take_image, "图库", 1, false, "ALBUM"))

        imageOperation.add(Operation(R.drawable.form_take_photo, "拍照", 0, false, "CAMERA"))
        imageOperation.add(Operation(R.drawable.form_take_image, "图库", 1, false, "ALBUM"))

        sexOperation.add(Operation(R.drawable.form_dot_black, "男", 0, false, 1))
        sexOperation.add(Operation(R.drawable.form_dot_black, "女", 1, false, 2))

        commonOperation.add(Operation(R.drawable.form_dot_black, "性别不符", 0))
        commonOperation.add(Operation(R.drawable.form_dot_black, "年龄不符", 0))
        commonOperation.add(Operation(R.drawable.form_dot_black, "身体特征有差别", 0))
        commonOperation.add(Operation(R.drawable.form_dot_black, "误判", 0))
        commonOperation.add(Operation(R.drawable.form_dot_black, "其他", 0))

        CommonPhrases.add(Operation(R.drawable.form_dot_black, "发现嫌疑人", 0))
        CommonPhrases.add(Operation(R.drawable.form_dot_black, "执行抓捕", 0))
        CommonPhrases.add(Operation(R.drawable.form_dot_black, "已抓到嫌疑人", 0))
        CommonPhrases.add(Operation(R.drawable.form_dot_black, "抓捕行动结束", 0))

    }

    /**
     * 具体时间选择
     *
     * @param context
     * @param showSpecificTime 是否显示时分
     * @param showDayTime      是否显示日
     * @param start 最小时间
     * @param finish 最大时间
     * @param select 默认选择时间
     * @param onDateListener 回调
     */
    fun showRealDateChoseView(context: Context, showSpecificTime: Boolean, showDayTime: Boolean, start: Date?, finish: Date?, select: Date?, onDateListener: OnDateListener?) {
        var selectDate = select
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
        // 设置可选年范围
        val span = 3L
        // 获取当前可选时间
        val nowDate = Date()
        /*可选范围*/
        val sDate: Date
        val eDate: Date
        /*将要设置启动时间，那么启动时间不得大于完结时间，如果完结时间为空，则随意设置，但不得小于当前时间，但如果开始时间不为空，则判断开始与当前时间大小*/
        if (finish == null) {
            sDate = start ?: Date(nowDate.time - span * 365 * 60 * 24 * 1000 * 60L)
            eDate = Date(sDate.time + (if (start == null) 2 else 1) * span * 365 * 60 * 24 * 1000 * 60L)
        } else {
            /*完结时间不为空，开始时间不得大于完结时间，则最大可设置时间不得大于完结时间*/
            eDate = Date(finish.time)
            sDate = start ?: Date(eDate.time - span * 365 * 60 * 24 * 1000 * 60L)
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
    fun setBottomChoseListView(context: Context, operations: List<Operation>, onTListener: OnTListener<Operation>?) {
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
    fun setBottomSelectListView(context: Context, title: String?, operations: List<Operation>, onTListener: OnTListener<List<Operation>>?) {
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

                val adapter = ConditionChoseAdapter(context, operations)
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
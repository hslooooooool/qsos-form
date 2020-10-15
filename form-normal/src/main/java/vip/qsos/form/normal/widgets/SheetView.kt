package vip.qsos.form.normal.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.normal.R

/**支持4级表格样式
 * @author : 华清松
 */
class SheetView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
    }

    data class Sheet(
            val type: Int = 0,
            val title: String,
            var value: String,
            val position: String,
            var notice: String = value,
            val child: ArrayList<Sheet> = arrayListOf()
    )

    private val mData: ArrayList<Sheet> = arrayListOf()

    private var mValueListener: OnTListener<Sheet>? = null

    /**当前表格最深的层级*/
    private var mDeep: Int = 0

    fun addValueListener(listener: OnTListener<Sheet>) {
        this.mValueListener = listener
    }

    fun setData(data: List<Sheet>) {
        mData.clear()
        data.forEach {
            addSheet(it)
        }
        flush()
    }

    private fun getPosition(index: Int, data: Sheet): Sheet {
        return data.child[index]
    }

    private fun addSheet(sheet: Sheet) {
        val p = sheet.position.split("-")
        // 当前表格项的层级
        val s = p.size
        if (s < 1) {
            // 编号不存在
            return
        }
        if (s == 1) {
            mData.add(p[0].toInt(), sheet)
            return
        }
        val ps = arrayOf<Int>()
        for (i in 1..s) {
            ps[i - 1] = p[i - 1].toInt()
        }
        if (s > 1) {
            var next: Sheet? = null
            ps.forEachIndexed { index, i ->
                when {
                    index < 1 -> {
                        // 第一位，从表格一级列表中进行值获取
                        next = mData[index]
                    }
                    index in 1 until s -> {
                        // 中间位置，进行循环取值，获取最后一位值
                        next = getPosition(i, next!!)
                    }
                    index == s - 1 -> {
                        // 最后一位，直接进行插值操作
                        next!!.child.add(i, sheet)
                    }
                }
            }
        }

        if (s > mDeep) {
            // 记录当前表格最深的层级
            mDeep = s
        }
    }

    private fun flush() {
        removeAllViews()
        mData.forEach {
            addSheetView(0, it, this)
        }
        invalidate()
    }

    private fun addSheetView(level: Int, sheet: Sheet, container: LinearLayout) {
        val isInput = sheet.type == 0
        if (isInput) {
            // 输入类型，添加输入控件
            val input = getInput(level, sheet)
            container.addView(input)
        } else {
            // 容器类型，添加容器控件
            val l = LinearLayout(context)
            l.background = ContextCompat.getDrawable(context, R.drawable.form_sheet_bg)
            l.orientation = VERTICAL
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, getWeight(sheet))
            l.layoutParams = params
            val title = getTitleView(level, sheet)
            l.addView(title)

            val size = sheet.child.size
            val a = size / 2
            val b = size % 2
            val c = when (b) {
                1 -> a + 1
                else -> a
            }
            for (i in 1..c) {
                val s = if (b == 0) {
                    arrayListOf(sheet.child[i * 2 - 2], sheet.child[i * 2 - 1])
                } else {
                    if (i * 2 < size) {
                        arrayListOf(sheet.child[i * 2 - 2], sheet.child[i * 2 - 1])
                    } else {
                        arrayListOf(sheet.child[i * 2 - 2])
                    }
                }
                val child = getContainer(level, sheet, s)
                l.addView(child)
            }
            container.addView(l)
        }
    }

    private fun getInput(level: Int, sheet: Sheet): LinearLayout {
        val container = LinearLayout(context)
        container.background = ContextCompat.getDrawable(context, R.drawable.form_sheet_bg)
        container.orientation = VERTICAL
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        container.layoutParams = params
        val title = getTitleView(level, sheet)
        container.addView(title)
        val input = getSheetInput(sheet)
        container.addView(input)
        return container
    }

    private fun getContainer(level: Int, parent: Sheet, child: List<Sheet>): LinearLayout {
        val container = LinearLayout(context)
        container.orientation = VERTICAL
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1F)
        container.layoutParams = params
        child.forEach {
            addSheetView(level + 1, it, container)
        }
        return container
    }

    private fun getTitleView(level: Int, sheet: Sheet): TextView {
        val title = TextView(context)
        title.text = sheet.title
        title.setTextColor(Color.BLACK)
        title.background = getTitleBackground(level)
        title.gravity = Gravity.CENTER
        title.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 100, 3F)
        title.textSize = 10f
        return title
    }

    private fun getSheetInput(sheet: Sheet): EditText {
        val input = EditText(context)
        input.id = sheet.position.hashCode()
        input.background = ContextCompat.getDrawable(context, R.drawable.form_sheet_input_bg)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, 100, 1F)
        input.layoutParams = params
        input.hint = sheet.title
        input.setText(sheet.value)
        input.textSize = 10f
        input.gravity = Gravity.CENTER
        input.inputType = EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
        input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    sheet.value = it.toString().trim()
                    mValueListener?.back(sheet)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        return input
    }

    private fun getTitleBackground(level: Int): Drawable {
        return when (level) {
            0 -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title1)!!
            1 -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title2)!!
            2 -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title3)!!
            else -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title4)!!
        }
    }

    private fun getWeight(sheet: Sheet): Float {
        return if (sheet.child.isEmpty()) 2F else 1F
    }
}
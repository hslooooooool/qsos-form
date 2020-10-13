package vip.qsos.form.normal.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
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
            var deep: Float = 1f,
            val child: ArrayList<Sheet> = arrayListOf()
    )

    private val mData: ArrayList<Sheet> = arrayListOf()

    private var mClickListener: OnTListener<Sheet>? = null

    fun setClickListener(listener: OnTListener<Sheet>) {
        this.mClickListener = listener
    }

    fun setData(data: List<Sheet>) {
        mData.clear()
        data.forEach {
            addSheet(it)
        }
        mData.forEach {
            setDeep(it)
        }
        flush()
    }

    private fun addSheet(sheet: Sheet) {
        val p = sheet.position.split("-")
        when (p.size) {
            1 -> {
                val p1 = p[0].toInt()
                mData.add(p1 - 1, sheet)
            }
            2 -> {
                val p1 = p[0].toInt()
                val p2 = p[1].toInt()
                mData[p1 - 1].child.add(p2 - 1, sheet)
            }
            3 -> {
                val p1 = p[0].toInt()
                val p2 = p[1].toInt()
                val p3 = p[2].toInt()
                mData[p1 - 1].child[p2 - 1].child.add(p3 - 1, sheet)
            }
            4 -> {
                val p1 = p[0].toInt()
                val p2 = p[1].toInt()
                val p3 = p[2].toInt()
                val p4 = p[3].toInt()
                mData[p1 - 1].child[p2 - 1].child[p3 - 1].child.add(p4 - 1, sheet)
            }
        }

    }

    private fun setDeep(sheet: Sheet, level: Float = 0f) {
        var l = level
        sheet.child.forEach { s1 ->
            l++
            setDeep(s1, level)
            s1.child.forEach { s2 ->
                l++
                setDeep(s2, level)
                s2.child.forEach { s3 ->
                    l++
                    setDeep(s3, level)
                    s3.child.forEach { s4 ->
                        l++
                        setDeep(s4, level)
                    }
                }
            }
        }
        sheet.deep = l
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
            val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, sheet.deep)
            println("表格：${sheet.title}，设置权重:${sheet.deep}")
            l.layoutParams = params
            l.minimumHeight = 200
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
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, sheet.deep)
        println("表格：${sheet.title}，设置权重:${sheet.deep}")
        container.layoutParams = params
        val title = getTitleView(level, sheet)
        container.addView(title)
        val input = getSheetInput(sheet)
        container.addView(input)
        return container
    }

    private fun getContainer(level: Int, parent: Sheet, child: List<Sheet>): LinearLayout {
        val container = LinearLayout(context)
        container.orientation = HORIZONTAL
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, parent.deep)
        container.layoutParams = params
        container.minimumHeight = 200
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
        title.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 100)
        title.textSize = 10f
        title.setOnClickListener {
            mClickListener?.back(sheet)
        }
        return title
    }

    private fun getSheetInput(sheet: Sheet): EditText {
        val input = EditText(context)
        input.id = sheet.position.hashCode()
        input.background = ContextCompat.getDrawable(context, R.drawable.form_sheet_bg)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, 100, 1f)
        input.layoutParams = params
        input.hint = sheet.title
        input.setText(sheet.value)
        input.textSize = 10f
        input.gravity = Gravity.CENTER
        input.inputType = EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
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
}
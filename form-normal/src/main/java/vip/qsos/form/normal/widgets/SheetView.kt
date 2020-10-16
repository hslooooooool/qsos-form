package vip.qsos.form.normal.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.ValueEntity
import vip.qsos.form.normal.R

/**支持 5 级表格样式
 * @author : 华清松
 */
class SheetView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val itemHeight = 200

    init {
        orientation = VERTICAL
    }

    data class Sheet(
            val type: ValueEntity.SheetFormat = ValueEntity.SheetFormat.TEXT,
            val title: String,
            var value: String,
            val position: String,
            var notice: String = value,
            var level: Int = 1,
            var height: Int = 1,
            var deep: Int = 1,
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
        // 计算每个表格的层级
        data.sortedBy {
            it.position
        }.forEach {
            val p = it.position.split("-")
            // 当前表格项的层级
            val s = p.size
            it.level = s
            if (s > mDeep) {
                // 记录当前表格最深的层级
                mDeep = s
            }
        }

        // 计算表格高度占比
        for (l in mDeep downTo 1) {
            if (l == 1) break

            // 高度占比
            val pp = HashMap<String, Int>()
            // 宽度占比
            val pd = HashMap<String, Int>()
            data.filter {
                it.level == l
            }.sortedBy {
                it.position
            }.forEach {
                val i = it.position.lastIndexOf("-")
                val p = it.position.substring(0, i)
                val s = pp[p] ?: 0
                pp[p] = s + it.height
                val d = pd[p] ?: 1
                if (d > it.deep) {
                    pd[p] = d
                }
            }
            pp.forEach { p ->
                data.find {
                    it.position == p.key
                }?.let {
                    it.height = p.value
                    it.deep = pd[p.key] ?: 1
                }
            }
        }

        // 表格平级转层级关系
        for (l in 1..mDeep) {
            data.filter {
                it.level == l
            }.sortedBy {
                it.position
            }.forEach {
                addSheet(l, it)
            }
        }

        flush()
    }

    private fun addSheet(level: Int, sheet: Sheet) {
        val p = sheet.position.split("-")
        val ps = arrayListOf<Int>()
        p.forEach {
            ps.add(it.toInt())
        }
        when {
            // 编号不存在
            level < 1 -> return
            // 第1级表格
            level == 1 -> {
                mData.add(sheet)
                mData.sortedBy { it.position }
            }
            // 第2级表格
            level == 2 -> {
                mData[ps[level - 2] - 1].child.add(sheet)
                mData[ps[level - 2] - 1].child.sortedBy { it.position }
            }
            // 第3级表格
            level == 3 -> {
                mData[ps[level - 3] - 1].child[ps[level - 2] - 1].child.add(sheet)
                mData[ps[level - 3] - 1].child[ps[level - 2] - 1].child.sortedBy { it.position }
            }
            // 第4级表格
            level == 4 -> {
                mData[ps[level - 4] - 1].child[ps[level - 3] - 1].child[ps[level - 2] - 1].child.add(sheet)
                mData[ps[level - 4] - 1].child[ps[level - 3] - 1].child[ps[level - 2] - 1].child.sortedBy { it.position }
            }
            // 第5级表格
            level == 5 -> {
                mData[ps[level - 5] - 1].child[ps[level - 4] - 1].child[ps[level - 3] - 1].child[ps[level - 2] - 1].child.add(sheet)
                mData[ps[level - 5] - 1].child[ps[level - 4] - 1].child[ps[level - 3] - 1].child[ps[level - 2] - 1].child.sortedBy { it.position }
            }
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
        val isInput = sheet.type == ValueEntity.SheetFormat.TEXT
        if (isInput) {
            // 输入类型，添加输入控件
            val input = SheetInputView(
                    context,
                    sheet.title, sheet.notice, sheet.value, sheet.type, sheet.deep,
                    getTitleBackground(level),
                    object : OnTListener<String> {
                        override fun back(t: String) {
                            sheet.value = t
                            mValueListener?.back(sheet)
                        }
                    })
            input.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, itemHeight)
            container.addView(input)
        } else {
            // 容器类型，添加容器控件
            val c = SheetContainerView(
                    context,
                    sheet.title, sheet.deep,
                    getTitleBackground(level)
            )
            c.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, itemHeight * sheet.height)
            container.addView(c)
            sheet.child.forEach {
                addSheetView(level + 1, it, c.mContainer)
            }
        }
    }

    private fun getTitleBackground(level: Int): Drawable {
        return when (level) {
            0 -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title1)!!
            1 -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title2)!!
            2 -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title3)!!
            3 -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title4)!!
            else -> ContextCompat.getDrawable(context, R.drawable.form_sheet_title5)!!
        }
    }

}
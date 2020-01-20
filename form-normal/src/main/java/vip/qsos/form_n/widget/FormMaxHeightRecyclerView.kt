package vip.qsos.form_n.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import vip.qsos.form_n.R

/**
 * @author : 华清松
 * 最大高度RecyclerView列表
 */
class FormMaxHeightRecyclerView : RecyclerView {
    private var mMaxHeight: Int = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize(context, attrs)
    }

    private fun initialize(context: Context, attrs: AttributeSet) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.FormMaxHeightRecyclerView)
        mMaxHeight = arr.getLayoutDimension(R.styleable.FormMaxHeightRecyclerView_formMaxHeight, mMaxHeight)
        arr.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, if (mMaxHeight > 0) {
            MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST)
        } else {
            heightMeasureSpec
        })
    }
}


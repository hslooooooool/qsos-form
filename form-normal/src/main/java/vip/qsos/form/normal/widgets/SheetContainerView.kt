package vip.qsos.form.normal.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import vip.qsos.form.normal.R

@SuppressLint("ViewConstructor")
class SheetContainerView @JvmOverloads constructor(
        context: Context,
        title: String,
        deep: Int,
        background: Drawable,
        attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var mTitle: TextView
    var mContainer: LinearLayout

    init {
        val p = LayoutInflater.from(context).inflate(R.layout.form_sheet_container_view, this, true)
        mTitle = p.findViewById(R.id.form_sheet_container_title)
        mTitle.layoutParams = LinearLayout.LayoutParams(80, LayoutParams.MATCH_PARENT)
        mContainer = p.findViewById(R.id.form_sheet_container)

        mTitle.text = title
        mTitle.background = background
        mTitle.setOnClickListener {
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
        }
    }

}
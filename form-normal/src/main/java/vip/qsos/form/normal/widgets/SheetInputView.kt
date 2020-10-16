package vip.qsos.form.normal.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.*
import vip.qsos.form.lib.callback.OnTListener
import vip.qsos.form.lib.model.ValueEntity
import vip.qsos.form.normal.R

@SuppressLint("ViewConstructor")
class SheetInputView @JvmOverloads constructor(
        context: Context,
        title: String,
        hint: String,
        content: String,
        type: ValueEntity.SheetFormat,
        deep: Int,
        background: Drawable,
        listener: OnTListener<String>,
        attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var mTitle: TextView
    var mInput: EditText

    init {
        val p = LayoutInflater.from(context).inflate(R.layout.form_sheet_input_view, this, true)
        mTitle = p.findViewById(R.id.form_sheet_title)
        mInput = p.findViewById(R.id.form_sheet_input)
        mTitle.layoutParams = LinearLayout.LayoutParams(deep * 50, LayoutParams.MATCH_PARENT)
        mTitle.text = title
        mInput.hint = if (TextUtils.isEmpty(hint)) {
            "请输入$title"
        } else {
            hint
        }
        mInput.setText(content)

        mTitle.background = background
        mTitle.setOnClickListener {
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
        }
        when (type) {
            ValueEntity.SheetFormat.NUMBER -> {
                mInput.inputType = EditorInfo.TYPE_CLASS_NUMBER
            }
            ValueEntity.SheetFormat.NUMBER_DECIMAL -> {
                mInput.inputType = EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_FLAG_DECIMAL
            }
            else -> {
                mInput.inputType = EditorInfo.TYPE_CLASS_TEXT
            }
        }
        mInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    listener.back(it.toString().trim())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }

}
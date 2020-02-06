package vip.qsos.form

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.main_activity.*
import vip.qsos.form_lib.base.BaseActivity
import vip.qsos.form_lib.base.FormAdapter

/**
 * @author : 华清松
 */
class MainActivity(
        override val layoutId: Int = R.layout.main_activity,
        override val reload: Boolean = true
) : BaseActivity() {

    private lateinit var mAdapter: FormAdapter
    private val mModel: MainViewModel by viewModels()

    override fun initData(savedInstanceState: Bundle?) {
        if (mModel.mForm.value == null) {
            mModel.mForm.value = FormUtil.Create.feedbackForm()
        }
        mAdapter = FormAdapter(mModel.mForm.value!!.formItems!!)
    }

    override fun initView() {
        form_list.layoutManager = LinearLayoutManager(this)
        form_list.adapter = mAdapter
        form_title.text = mModel.mForm.value!!.title
        mAdapter.notifyDataSetChanged()

        form_submit.setOnClickListener {
            Log.d("表单内容", Gson().toJson(mModel.mForm.value))
        }
    }

    override fun getData() {

    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val v = currentFocus
        if (v != null && v is EditText) {
            val outRect = Rect()
            v.getGlobalVisibleRect(outRect)
            if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                v.clearFocus()
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (imm.isActive) {
                    if (v.windowToken != null) {
                        imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                }
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
        return super.dispatchTouchEvent(event)
    }
}
package vip.qsos.form

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_activity.*
import vip.qsos.form_lib.base.BaseActivity
import vip.qsos.form_lib.base.FormAdapter
import vip.qsos.form_lib.config.FormHelper
import vip.qsos.form_lib.model.FormEntity
import vip.qsos.form_lib.model.FormItemEntity


/**
 * @author : 华清松
 */
class MainActivity(
        override val layoutId: Int = R.layout.main_activity,
        override val reload: Boolean = true
) : BaseActivity() {

    private lateinit var mAdapter: FormAdapter
    private val mList: ArrayList<FormItemEntity<*>> = arrayListOf()
    private lateinit var mForm: FormEntity

    override fun initData(savedInstanceState: Bundle?) {
        FormHelper.init(FormConfigImpl())
    }

    override fun initView() {
        mAdapter = FormAdapter(mList)
        form_list.layoutManager = LinearLayoutManager(this)
        form_list.adapter = mAdapter
    }

    override fun getData() {
        mForm = FormUtil.Create.feedbackForm()
        mList.addAll(mForm.formItems!!)
        mAdapter.notifyDataSetChanged()
        form_title.text = mForm.title
        form_submit.text = mForm.submitName
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
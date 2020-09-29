package vip.qsos.form

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_activity.*
import vip.qsos.form.lib.FormAdapter
import vip.qsos.form.normal.utils.FormVerifyUtils

/**
 * @author : 华清松
 */
class MainActivity : AppCompatActivity() {

    private var mAdapter: FormAdapter = FormAdapter(arrayListOf())
    private val mModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initView()

        initData()

    }

    private fun initView() {
        form_list.layoutManager = LinearLayoutManager(this)
        form_list.adapter = mAdapter
        form_submit.isEnabled = false

        form_submit.setOnClickListener {
            val verify = FormVerifyUtils.verify(mModel.mForm.value!!)
            val itemName = mModel.mForm.value!!.formItems[verify.info.itemIndex].title
            if (verify.pass) {
                Toast.makeText(this, verify.msg, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, itemName + "-" + verify.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initData() {
        mModel.mForm.observe(this, Observer {
            it?.let {
                mAdapter.data = it.formItems
                form_title.text = it.title
                form_submit.isEnabled = true
                mAdapter.notifyDataSetChanged()
            } ?: Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show()
        })
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
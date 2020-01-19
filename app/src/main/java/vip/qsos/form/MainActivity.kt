package vip.qsos.form

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_activity.*
import vip.qsos.form.base.BaseActivity
import vip.qsos.form.base.FormAdapter
import vip.qsos.form.config.FormHelper
import vip.qsos.form.model.FormDatabase
import vip.qsos.form.model.entity.FormItem

/**
 * @author : 华清松
 */
class MainActivity(
        override val layoutId: Int = R.layout.main_activity,
        override val reload: Boolean = true
) : BaseActivity() {

    private lateinit var mAdapter: FormAdapter
    private val mList: ArrayList<FormItem> = arrayListOf()

    override fun initData(savedInstanceState: Bundle?) {
        FormHelper.init(FormConfigImpl())
        FormDatabase.create(this)
    }

    override fun initView() {
        mAdapter = FormAdapter(mList)
        form_list.layoutManager = LinearLayoutManager(this)
        form_list.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        form_list.adapter = mAdapter
    }

    override fun getData() {
        for (i in 0..5) {
            mList.add(FormItem(valueType = i))
        }
        mAdapter.notifyDataSetChanged()
    }
}
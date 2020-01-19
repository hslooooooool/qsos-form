package vip.qsos.form.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vip.qsos.form.R

/**
 * @author : 华清松
 * Base Activity
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    open lateinit var mContext: Context

    abstract val layoutId: Int?

    /**视图重载是否重新加载数据*/
    abstract val reload: Boolean

    override val defLayoutId: Int = R.layout.base_default

    override var isActive: Boolean = false
        protected set

    override var isOrientation: Boolean = true
        protected set

    /*注意调用顺序*/

    /**初始化数据*/
    abstract fun initData(savedInstanceState: Bundle?)

    /**初始化试图*/
    abstract fun initView()

    /**获取数据*/
    abstract fun getData()

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        mContext = this

        // 竖屏显示
        if (isOrientation) requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        initData(bundle)

        if (layoutId == null) {
            setContentView(defLayoutId)
        } else {
            setContentView(layoutId!!)
            initView()
        }

    }

    override fun onStart() {
        super.onStart()
        isActive = true
    }

    override fun onResume() {
        super.onResume()
        if (reload) {
            getData()
        }
    }

    override fun onStop() {
        super.onStop()
        isActive = false
    }
}

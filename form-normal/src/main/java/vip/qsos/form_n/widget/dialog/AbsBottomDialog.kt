package vip.qsos.form_n.widget.dialog

import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import vip.qsos.form_n.R

/**
 * @author : 华清松
 * 自定义底部弹窗
 */
abstract class AbsBottomDialog : DialogFragment() {

    var mParentView: View? = null
    open var layoutRes: Int = 0

    /**是否全屏*/
    abstract var isFillParent: Boolean

    var height: Int = -1

    var dimAmount: Float = DEFAULT_DIM

    var cancelOutside: Boolean = true

    var fragmentTag: String = TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FormBottomDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(cancelOutside)
        mParentView = inflater.inflate(layoutRes, container, false)
        bindView(this)
        return mParentView
    }

    abstract fun bindView(dialog: AbsBottomDialog)


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val params = window!!.attributes
        params.dimAmount = dimAmount
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        if (isFillParent) {
            params.height = WindowManager.LayoutParams.MATCH_PARENT
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
        }
        params.gravity = Gravity.BOTTOM

        window.attributes = params
    }

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, fragmentTag)
    }

    inline fun <reified T : View?> findViewById(@IdRes Id: Int): T {
        val parentView = this.mParentView
        return if (parentView != null) parentView.findViewById<T>(Id) else null as T
    }

    companion object {
        private const val TAG = "BottomDialog"

        private const val DEFAULT_DIM = 0.2f
    }
}

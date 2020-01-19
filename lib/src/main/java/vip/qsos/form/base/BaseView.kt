package vip.qsos.form.base

/**
 * @author : 华清松
 * View 接口
 */
interface BaseView {
    /**视图ID*/
    val defLayoutId: Int

    /**是否处于前台*/
    val isActive: Boolean

    /**是否竖屏显示*/
    val isOrientation: Boolean
}
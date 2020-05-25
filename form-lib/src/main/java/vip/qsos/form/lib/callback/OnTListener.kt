package vip.qsos.form.lib.callback

/**泛型回调
 * @author : 华清松
 */
interface OnTListener<T> {

    /**获取回调对象*/
    fun back(t: T)

}

package vip.qsos.form.callback

/**
 * @author : 华清松
 * 类说明：泛型回调
 */
interface OnTListener<T> {

    /**获取回调对象*/
    fun back(t: T)

}

package vip.qsos.form_lib.model

abstract class AbsFormValue<T : FormValueType> {

    /**真实值对象*/
    open var value: T? = null

}

interface FormValueType {
    val type: Int
}
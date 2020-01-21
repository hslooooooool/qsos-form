package vip.qsos.form_lib.model

import androidx.room.Ignore

abstract class AbsFormValue<T : AbsFormValue<T>> {
    open var value: String = ""

    @Ignore
    open var obj: T? = null
        get() {
            if (field == null) {
                field = getEntity(value)
            }
            return field
        }
        set(value) {
            field = value
            setEntity(value)
        }

    /**推荐做法:将 value 通过 gson 转为 T */
    abstract fun getEntity(value: String): T?

    abstract fun transValue(): String

    abstract fun transValue(obj: T?): String

    fun setEntity(obj: T?) {
        this.value = transValue(obj)
    }

}
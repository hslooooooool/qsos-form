package vip.qsos.form.lib.model

/**
 * @author : 华清松
 */
interface IValue {
    val valueType: Int
    fun json(): String?
}

class EmptyValue : IValue {
    override val valueType: Int
        get() = -1

    override fun json(): String? {
        return null
    }
}
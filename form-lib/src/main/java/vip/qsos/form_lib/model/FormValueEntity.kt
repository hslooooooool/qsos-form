package vip.qsos.form_lib.model

import androidx.room.*

/**
 * @author : 华清松
 * 表单项值实体类
 * @param id 表单项值ID
 * @param formItemId 外键-表单项ID
 * @param limit 限制条件
 * @param editable 是否可编辑
 * @param position 顺序
 * @param value 真实值对象的JSON格式数据,根据表单列表项的值类型,转化对应格式类型值
 */
@Entity(tableName = "formItemValue",
        foreignKeys = [
            ForeignKey(entity = FormItemEntity::class, parentColumns = ["id"], childColumns = ["formItemId"], onDelete = ForeignKey.CASCADE)
        ],
        indices = [
            Index(value = ["id"], unique = true)
        ]
)
data class FormValueEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        var formItemId: Long? = null,
        var limit: String? = null,
        var editable: Boolean = true,
        var position: Int = 1,
        var value: String? = null
) {
    @Ignore
    private var mValue: Any? = null

    fun setRealValue(value: Any) {
        this.mValue = value
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getRealValue(): T? {
        return try {
            mValue as T?
        } catch (e: Exception) {
            null
        }
    }

}
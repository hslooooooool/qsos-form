package qsos.core.form.db.entity

import androidx.room.*
import vip.qsos.form.model.entity.*

/**
 * @author : 华清松
 * 表单项值实体类
 * @param id 表单项值ID
 *
 * @param formItemId 外键-表单项ID
 *
 * @param limitType 此值类型限制
 * @param limitEdit 此值是否可编辑
 * @param position 此值下标位置，用于排序
 */
@Entity(tableName = "formItemValue",
        foreignKeys = [
            ForeignKey(entity = FormItem::class, parentColumns = ["id"], childColumns = ["formItemId"], onDelete = ForeignKey.CASCADE)
        ],
        indices = [
            Index(value = ["id"], unique = true)
        ]
)
data class Value(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        var formItemId: Long? = null,
        var limitType: String? = null,
        var limitEdit: Boolean = false,
        var position: Int = 1
) {
    /**输入*/
    @Embedded
    var text: FormValueOfText? = null
        get() = if (field != null) field else FormValueOfText()
    /**时间*/
    @Embedded
    var time: FormValueOfTime? = null
        get() = if (field != null) field else FormValueOfTime()
    /**选择*/
    @Embedded
    var check: FormValueOfCheck? = null
        get() = if (field != null) field else FormValueOfCheck()
    /**人员*/
    @Embedded
    var user: FormValueOfUser? = null
        get() = if (field != null) field else FormValueOfUser()
    /**附件*/
    @Embedded
    var file: FormValueOfFile? = null
        get() = if (field != null) field else FormValueOfFile()
    /**位置*/
    @Embedded
    var location: FormValueOfLocation? = null
        get() = if (field != null) field else FormValueOfLocation()

    fun newText(text: FormValueOfText, formItemId: Long? = null): Value {
        this.formItemId = formItemId
        this.text = text
        return this
    }

    fun newTime(time: FormValueOfTime, formItemId: Long? = null): Value {
        this.formItemId = formItemId
        this.time = time
        return this
    }

    fun newCheck(check: FormValueOfCheck, formItemId: Long? = null): Value {
        this.formItemId = formItemId
        this.check = check
        return this
    }

    fun newUser(user: FormValueOfUser, formItemId: Long? = null): Value {
        this.formItemId = formItemId
        this.user = user
        return this
    }

    fun newFile(file: FormValueOfFile, formItemId: Long? = null): Value {
        this.formItemId = formItemId
        this.file = file
        return this
    }

    fun newLocation(location: FormValueOfLocation, formItemId: Long? = null): Value {
        this.formItemId = formItemId
        this.location = location
        return this
    }

}
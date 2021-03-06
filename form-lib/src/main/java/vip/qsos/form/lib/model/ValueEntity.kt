package vip.qsos.form.lib.model

import java.util.*

/**表单项值实体类
 * @author : 华清松
 */
class ValueEntity {

    enum class Type { IMAGE, ALBUM, VIDEO, AUDIO, FILE }

    /**暂定表格可录入的值类型：文本、正整数、正小数*/
    enum class SheetFormat { TEXT, NUMBER, NUMBER_DECIMAL, CONTAINER }

    var valueType: Int = -1

    /*0文本或1输入*/

    var content: String? = ""

    /*2选项*/

    var ckId: String? = null
    var ckName: String = ""
    var ckValue: String = ckName
    var ckChecked: Boolean = false

    /*3时间*/

    var time: Long = 0L
    var timeLimitMin: Long = 0L
    var timeLimitMax: Long = 0L

    /*4用户*/

    var userId: String? = null
    var userName: String = ""
    var userDesc: String = ""
    var userAvatar: String? = null

    /*5文件*/

    var fileId: String? = null
    var fileName: String = ""
    var fileType: Type = Type.FILE
    var fileUrl: String? = null
    var fileCover: String? = null
        get() {
            field = when {
                field != null -> field
                fileUrl != null -> fileUrl
                else -> null
            }
            return field
        }

    /*6位置*/

    var locName: String? = null
    var locX: Double = 0.0
    var locY: Double = 0.0

    /*7表格*/

    var sheetTitle: String = ""
    var sheetPosition: String = ""
    var sheetType: Int = 0
    var sheetFormat: String = "TEXT"
    var sheetContent: String = ""
    var sheetNotice: String = ""

    constructor()

    /**
     * @param content 文本内容
     * */
    constructor (
            content: String? = "",
            valueType: Int = 0
    ) {
        this.valueType = valueType
        this.content = content
    }

    /**
     * @param ckId 选项ID
     * @param ckName 选项名称
     * @param ckValue 选项值
     * @param ckChecked 是否被选
     * */
    constructor(
            ckId: String? = null,
            ckName: String,
            ckValue: String,
            ckChecked: Boolean
    ) {
        this.valueType = 2
        this.ckId = ckId
        this.ckName = ckName
        this.ckValue = ckValue
        this.ckChecked = ckChecked
    }

    /**
     * @param time 设置的时间毫秒数
     * @param timeLimitMin 可选最小时间毫秒数
     * @param timeLimitMax 可选最大时间毫秒数
     * */
    constructor(
            time: Long = 0L,
            timeLimitMin: Long = 0L,
            timeLimitMax: Long = 0L
    ) {
        this.valueType = 3
        this.time = time
        this.timeLimitMin = timeLimitMin
        this.timeLimitMax = timeLimitMax
    }

    /**
     * @param userId 用户ID
     * @param userName 用户名称
     * @param userDesc 用户描述，推荐为手机号
     * @param userAvatar 用户头像链接，http://qsos.vip/img/logo.png
     * */
    constructor(
            userName: String = "",
            userDesc: String = "",
            userAvatar: String? = null,
            userId: String? = null
    ) {
        this.valueType = 4
        this.userId = userId
        this.userName = userName
        this.userDesc = userDesc
        this.userAvatar = userAvatar
    }

    /**
     * @param fileId 文件ID
     * @param fileName 文件名称
     * @param fileType 文件类型 IMAGE, VIDEO, AUDIO, FILE
     * @param fileUrl 文件服务器路径地址，如http://qsos.vip/file/logo.png
     * @param fileCover 文件封面地址，如http://qsos.vip/file/logo.png或/0/data/app1/logo.png
     * */
    constructor(
            fileId: String? = null,
            fileName: String = "",
            fileUrl: String? = null,
            fileCover: String? = null,
            fileType: Type = Type.FILE
    ) {
        this.valueType = 5
        this.fileId = fileId
        this.fileName = fileName
        this.fileType = fileType
        this.fileUrl = fileUrl
        this.fileCover = fileCover
    }

    /**
     * @param locName 位置名称
     * @param locX 位置经度
     * @param locY 位置维度
     * */
    constructor(
            locX: Double = 0.0,
            locY: Double = 0.0,
            locName: String = "未知地址"
    ) {
        this.valueType = 6
        this.locName = locName
        this.locX = locX
        this.locY = locY
    }

    /**
     * @param sheetTitle 表格名称
     * @param sheetPosition 表格位置
     * @param sheetContent 表格内容
     * @param sheetNotice 表格提示
     * @param sheetType 表格类型 -1 容器，0 输入
     * @param sheetFormat 表格内容格式
     * */
    constructor(
            sheetTitle: String,
            sheetPosition: String,
            sheetContent: String,
            sheetNotice: String,
            sheetType: Int = 0,
            sheetFormat: String = "TEXT"
    ) {
        this.valueType = 7
        this.sheetTitle = sheetTitle
        this.sheetPosition = sheetPosition
        this.sheetType = sheetType
        this.sheetContent = sheetContent
        this.sheetNotice = sheetNotice
        this.sheetFormat = sheetFormat
    }

    companion object {
        fun getFileTypeByMime(fileType: String?): Type {
            return when (fileType?.toUpperCase(Locale.ENGLISH)) {
                Type.IMAGE.name -> Type.IMAGE
                Type.ALBUM.name -> Type.ALBUM
                Type.VIDEO.name -> Type.VIDEO
                Type.AUDIO.name -> Type.AUDIO
                else -> Type.FILE
            }
        }
    }
}
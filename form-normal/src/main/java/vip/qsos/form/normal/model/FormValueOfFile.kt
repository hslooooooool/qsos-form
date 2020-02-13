package vip.qsos.form.normal.model

import vip.qsos.form.lib.model.AbsValue
import java.util.*

/**
 * @author : 华清松
 *
 * 表单项值-文件实体类
 * @param fileId 文件ID
 * @param fileName 文件名称
 * @param fileType 文件类型
 * @param filePath 文件本地路径地址，如/0/data/app1/logo.png
 * @param fileUrl 文件服务器路径地址，如http://qsos.vip/file/logo.png
 * @param fileCover 文件封面地址，如http://qsos.vip/file/logo.png或/0/data/app1/logo.png
 * */
data class FormValueOfFile(
        var fileId: String? = null,
        var fileName: String = "",
        var fileType: Type = Type.FILE,
        var filePath: String? = null,
        var fileUrl: String? = filePath,
        var fileCover: String? = filePath ?: fileUrl
) : AbsValue() {

    enum class Type { IMAGE, VIDEO, AUDIO, FILE }

    companion object {
        fun getFileTypeByMime(fileType: String?): Type {
            return when (fileType?.toUpperCase(Locale.ENGLISH)) {
                Type.IMAGE.name -> Type.IMAGE
                Type.VIDEO.name -> Type.VIDEO
                Type.AUDIO.name -> Type.AUDIO
                else -> Type.FILE
            }
        }
    }
}
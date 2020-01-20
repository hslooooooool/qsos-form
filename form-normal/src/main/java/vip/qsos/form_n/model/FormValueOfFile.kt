package vip.qsos.form_n.model

import android.text.TextUtils
import java.util.*

/**
 * @author : 华清松
 * 表单项值-文件实体类
 * @param fileId 文件ID
 * @param fileName 文件名称
 * @param fileType 文件类型
 * @param filePath 文件本地路径地址，如/0/data/app1/logo.png
 * @param fileUrl 文件服务器路径地址，如http://qsos.vip/file/logo.png
 * @param fileCover 文件封面地址，如http://qsos.vip/file/logo.png或/0/data/app1/logo.png
 * */
data class FormValueOfFile(
        var fileId: String? = "",
        var fileName: String? = null,
        var fileType: String? = null,
        var filePath: String? = null,
        var fileUrl: String? = null,
        var fileCover: String? = null
) {
    companion object {
        /**根据文件mime类型区分为以下几大类，用于表单附件缩略图展示*/
        fun getFileTypeByMime(fileType: String?): String {
            if (TextUtils.isEmpty(fileType)) {
                return "FILE"
            }
            fileType!!
            fileType.toLowerCase(Locale.CHINA)
            return when {
                fileType.endsWith("png") || fileType.endsWith("jpg") || fileType.endsWith("jpeg") -> "IMAGE"
                fileType.endsWith("amr") || fileType.endsWith("wav") || fileType.endsWith("raw") || fileType.endsWith("mp3") -> "AUDIO"
                fileType.endsWith("3gp") || fileType.endsWith("mp4") || fileType.endsWith("avi") -> "VIDEO"
                else -> "FILE"
            }
        }
    }
}
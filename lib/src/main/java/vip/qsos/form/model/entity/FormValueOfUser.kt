package vip.qsos.form.model.entity

/**
 * @author : 华清松
 * 表单项值-人员实体类
 * @param userId 用户ID
 * @param userName 用户名称
 * @param userDesc 用户描述，推荐为手机号
 * @param userAvatar 用户头像链接，http://qsos.vip/img/logo.png
 */
data class FormValueOfUser(
        var userId: String? = null,
        var userName: String? = null,
        var userDesc: String? = null,
        var userAvatar: String? = null
)
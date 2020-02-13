package vip.qsos.form.normal

/**
 * @author : 华清松
 *
 * 表单列表项帮助类
 */
object FormItemHelper : IFormItemConfig {

    var mIFormItemConfig: IFormItemConfig? = null

    /**配置*/
    fun init(formItemConfig: IFormItemConfig) {
        this.mIFormItemConfig = formItemConfig
    }

}
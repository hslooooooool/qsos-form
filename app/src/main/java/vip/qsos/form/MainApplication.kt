package vip.qsos.form

import android.app.Application
import vip.qsos.form_lib.config.FormHelper

/**
 * @author : 华清松
 * TODO 类说明，描述此类的类型和用途
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        /**配置表单*/
        FormHelper.init(FormConfigImpl())
    }

}
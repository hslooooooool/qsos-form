package vip.qsos.form

import android.app.Application
import vip.qsos.form.lib.config.FormHelper

/**
 * @author : 华清松
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        /**配置表单*/
        FormHelper.init(FormConfigImpl())
    }

}
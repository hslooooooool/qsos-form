package vip.qsos.form

import android.app.Application
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import vip.qsos.form.lib.FormTransHelper
import vip.qsos.form.lib.config.FormHelper
import vip.qsos.form.normal.utils.FormTransUtils
import vip.qsos.utils_net.lib.APIServer
import vip.qsos.utils_net.lib.mock.IMockData

/**
 * @author : 华清松
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        /**配置表单*/
        FormHelper.init(FormConfigImpl())
        FormTransHelper.init(FormTransUtils())

        // 开启网络请求Mock数据
        IMockData.openMockData = true
        IMockData.dataBySdCard = false
        IMockData.requestTime = 0L

        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptors.add(loggingInterceptor)
        // 初始化网络请求
        APIServer.init(
                APIServer.Config(this, "http://192.168.2.207:8085/", interceptor = interceptors)
        )
    }

}
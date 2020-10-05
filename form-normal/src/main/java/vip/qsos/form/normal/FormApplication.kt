package vip.qsos.form.normal

import android.app.Application

/**$
 * @author : 华清松
 */
class FormApplication : Application() {

    companion object {
        var application: FormApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
package vip.qsos.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vip.qsos.form.lib.model.FormEntity
import vip.qsos.utils_net.lib.callback.HttpLiveData
import vip.qsos.utils_net.lib.retrofit.retrofitWithBaseResult

/**
 * @author : 华清松
 */
class MainViewModel : ViewModel() {
    private val mFormData: HttpLiveData<FormEntity?> = HttpLiveData()
    var sceneType: String? = null

    val mForm: HttpLiveData<FormEntity?>
        get() {
            if (mFormData.value == null) {
                loadFormDemo()
            }
            return mFormData
        }

    private fun loadFormDemo() = viewModelScope.launch {
        retrofitWithBaseResult<FormEntity> {
            request {
                when (sceneType) {
                    "1" -> FormService.INSTANCE.demo1(sceneType)
                    "2" -> FormService.INSTANCE.demo2(sceneType)
                    "3" -> FormService.INSTANCE.demo3(sceneType)
                    "4" -> FormService.INSTANCE.demo4(sceneType)
                    "5" -> FormService.INSTANCE.demo5(sceneType)
                    else -> FormService.INSTANCE.demo0(sceneType)
                }
            }
            onFailed { _, _, e ->
                e?.printStackTrace()
            }
            onSuccess {
                it?.let {
                    mForm.postValue(it)
                }
            }
        }
    }
}
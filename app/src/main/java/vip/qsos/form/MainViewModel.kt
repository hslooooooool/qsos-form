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

    val mForm: HttpLiveData<FormEntity?>
        get() {
            if (mFormData.value == null) {
                loadFormDemo()
            }
            return mFormData
        }

    private fun loadFormDemo() = viewModelScope.launch {
        retrofitWithBaseResult<FormEntity> {
            request { FormService.INSTANCE.demo() }
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
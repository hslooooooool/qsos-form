package vip.qsos.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vip.qsos.form.lib.model.FormEntity

/**
 * @author : 华清松
 */
class MainViewModel : ViewModel() {
    val mForm: MutableLiveData<FormEntity> = MutableLiveData()
}
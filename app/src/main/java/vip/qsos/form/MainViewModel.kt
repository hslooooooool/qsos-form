package vip.qsos.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vip.qsos.form_lib.model.FormEntity

/**
 * @author : 华清松
 * TODO 类说明，描述此类的类型和用途
 */
class MainViewModel : ViewModel() {
    val mForm: MutableLiveData<FormEntity> = MutableLiveData()
}
package vip.qsos.form

import retrofit2.http.GET
import vip.qsos.form.lib.model.FormEntity
import vip.qsos.form.mock.FormMockData
import vip.qsos.utils_net.lib.APIServer

interface FormService {

    companion object {
        val INSTANCE: FormService by lazy {
            APIServer.api(
                    cls = FormService::class.java,
                    config = APIServer.APIConfig(
                            baseUrl = "http://192.168.3.8:8085/",
                            timeout = 3000L,
                            mockDataList = arrayListOf(FormMockData())
                    )
            )
        }
    }

    @GET("/api/form/demo")
    suspend fun demo(): HttpResult<FormEntity>

}
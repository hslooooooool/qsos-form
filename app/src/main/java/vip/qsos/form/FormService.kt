package vip.qsos.form

import retrofit2.http.GET
import retrofit2.http.Query
import vip.qsos.form.lib.model.FormEntity
import vip.qsos.form.mock.FormMockData
import vip.qsos.utils_net.lib.APIServer

interface FormService {

    companion object {
        val INSTANCE: FormService by lazy {
            APIServer.api(
                    cls = FormService::class.java,
                    config = APIServer.APIConfig(
                            baseUrl = "http://192.168.2.207:8085/",
                            timeout = 3000L,
                            mockDataList = arrayListOf(FormMockData())
                    )
            )
        }
    }

    @GET("/api/form/demo")
    suspend fun demo(
            @Query("sceneType") sceneType: String? = null
    ): HttpResult<FormEntity>

}
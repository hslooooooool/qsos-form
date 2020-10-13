package vip.qsos.form

import retrofit2.http.GET
import retrofit2.http.Query
import vip.qsos.form.lib.model.FormEntity
import vip.qsos.form.mock.*
import vip.qsos.utils_net.lib.APIServer

interface FormService {

    companion object {
        val INSTANCE: FormService by lazy {
            APIServer.api(
                    cls = FormService::class.java,
                    config = APIServer.APIConfig(
                            baseUrl = "http://192.168.3.131:8085/",
                            timeout = 3000L,
                            mockDataList = arrayListOf(
                                    FormMockData0(),
                                    FormMockData1(),
                                    FormMockData2(),
                                    FormMockData3(),
                                    FormMockData4(),
                                    FormMockData5()
                            )
                    )
            )
        }
    }

    @GET("/api/form/demo0")
    suspend fun demo0(
            @Query("sceneType") sceneType: String? = null
    ): HttpResult<FormEntity>

    @GET("/api/form/demo1")
    suspend fun demo1(
            @Query("sceneType") sceneType: String? = null
    ): HttpResult<FormEntity>

    @GET("/api/form/demo2")
    suspend fun demo2(
            @Query("sceneType") sceneType: String? = null
    ): HttpResult<FormEntity>

    @GET("/api/form/demo3")
    suspend fun demo3(
            @Query("sceneType") sceneType: String? = null
    ): HttpResult<FormEntity>

    @GET("/api/form/demo4")
    suspend fun demo4(
            @Query("sceneType") sceneType: String? = null
    ): HttpResult<FormEntity>

    @GET("/api/form/demo5")
    suspend fun demo5(
            @Query("sceneType") sceneType: String? = null
    ): HttpResult<FormEntity>

}
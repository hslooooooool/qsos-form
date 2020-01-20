package vip.qsos.form_n.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @author : 华清松
 * Kotlin协程配置数据库请求处理逻辑
 */
open class CoroutineDbScope {

    class Dsl<ResultType> {
        var db: (() -> ResultType?)? = null
        var onSuccess: ((ResultType?) -> Any?)? = null
        var onFail: ((Exception) -> Any?)? = {

        }
    }

    class DslByComplete {
        var db: () -> Any? = {
            throw Exception("未进行数据库操作")
        }
        var onSuccess: () -> Any? = {}
        var onFail: ((Exception) -> Any?)? = {

        }
    }
}

/**
 * @author : 华清松
 * 常用的数据库协程请求，返回查询结果
 */
fun <ResultType> CoroutineScope.db(
        dsl: CoroutineDbScope.Dsl<ResultType>.() -> Unit
) {
    val dbCoroutine = CoroutineDbScope.Dsl<ResultType>()
    dbCoroutine.dsl()
    this.launch(Dispatchers.Main) {
        val work = async(Dispatchers.IO) {
            try {
                dbCoroutine.db?.invoke()
            } catch (e: Exception) {
                dbCoroutine.onFail?.invoke(e)
                null
            }
        }
        work.await()?.let {
            dbCoroutine.onSuccess?.invoke(it)
        }
    }
}

/**
 * @author : 华清松
 * 常用的数据库协程请求，无返回结果
 */
fun CoroutineScope.dbComplete(
        dsl: CoroutineDbScope.DslByComplete.() -> Unit
) {
    val dbCoroutine = CoroutineDbScope.DslByComplete()
    dbCoroutine.dsl()
    this.launch(Dispatchers.Main) {
        val work = async(Dispatchers.IO) {
            try {
                dbCoroutine.db.invoke()
            } catch (e: Exception) {
                dbCoroutine.onFail?.invoke(e)
                null
            }
        }
        work.await().let {
            dbCoroutine.onSuccess.invoke()
        }
    }
}

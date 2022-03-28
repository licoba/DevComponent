package afkt_replace.core.lib.network

import afkt_replace.core.lib.network.common.OkHttpBuilderGlobal
import android.content.Context
import dev.DevHttpManager

/**
 * detail: Http Core Lib
 * @author Ttt
 */
object HttpCoreLibrary {

    // 全局通用 OkHttp Builder
    private val mOkHttpBuilderGlobal = OkHttpBuilderGlobal()

    // =============
    // = 对外公开方法 =
    // =============

    /**
     * 初始化 OkHttp 管理库 ( Retrofit 多 BaseUrl 等 )
     * @param context Context
     */
    fun initialize(context: Context) {
        // 设置全局 OkHttp Builder 接口对象
        DevHttpManager.setOkHttpBuilder(
            mOkHttpBuilderGlobal
        )
    }
}
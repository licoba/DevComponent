package afkt_replace.core.lib.debug_assist.engine

import afkt_replace.core.lib.engine.debug.IDebugEngine
import android.app.Activity
import okhttp3.OkHttpClient

/**
 * detail: Debug 编译辅助开发 Engine 实现
 * @author Ttt
 */
class DevDebugImpl : IDebugEngine {

    override fun setDebugFunction(show: Boolean) {
    }

    override fun showDebugFunction(activity: Activity?) {
    }

    override fun removeDebugFunction(activity: Activity?) {
    }

    override fun addInterceptor(
        builder: OkHttpClient.Builder?,
        moduleName: String?
    ) {
    }

    override fun environmentTips() {
    }
}
package afkt_replace.core.lib.engine.debug

import android.app.Activity
import okhttp3.OkHttpClient

/**
 * detail: Debug 编译辅助开发 Engine 接口
 * @author Ttt
 */
interface IDebugEngine {

    /**
     * 设置 Debug 功能开关
     * @param show 是否显示 Debug 功能
     */
    fun setDebugFunction(show: Boolean)

    /**
     * 显示 Debug 功能
     * @param activity 所属 Activity
     */
    fun showDebugFunction(activity: Activity?)

    /**
     * 移除 Debug 功能
     * @param activity 所属 Activity
     */
    fun removeDebugFunction(activity: Activity?)

    /**
     * 添加抓包拦截器
     * @param builder    OkHttpClient Builder
     * @param moduleName 模块名 ( 要求唯一性 )
     */
    fun addInterceptor(
        builder: OkHttpClient.Builder?,
        moduleName: String?
    )

    /**
     * 当前环境提示
     */
    fun environmentTips()
}
package dev.core.app

import android.util.Log
import dev.DevUtils
import dev.core.lib.base.BaseAppContext
import dev.core.lib.engine.compress.LubanEngineImpl
import dev.core.lib.engine.image.GlideEngineImpl
import dev.core.lib.engine.json.GsonEngineImpl
import dev.core.lib.engine.log.DevLoggerEngineImpl
import dev.core.lib.engine.media.PictureSelectorEngineImpl
import dev.core.lib.engine.permission.DevPermissionEngineImpl
import dev.core.property.Bugly
import dev.core.property.BuglyConfig
import dev.core.property.defaultBuglyConfig
import dev.engine.compress.DevCompressEngine
import dev.engine.image.DevImageEngine
import dev.engine.json.DevJSONEngine
import dev.engine.log.DevLogEngine
import dev.engine.media.DevMediaEngine
import dev.engine.permission.DevPermissionEngine
import dev.utils.LogPrintUtils
import dev.utils.common.StringUtils

/**
 * detail: App Base Application
 * @author Ttt
 */
open class AppContext : BaseAppContext() {

    override fun onCreate() {
        super.onCreate()

        // 初始化 DevUtils debug 相关配置
        if (AppDebug.isOpenDebug()) DevUtils.openDebug()
        // 是否打印日志
        if (AppDebug.isOpenLog()) {
            DevUtils.openLog()
            // 日志拦截 ( 方便调试 )
            devLogInterceptor()
        }

        // 是否使用默认 Engine 配置
        if (isEngineConfig()) initializeEngine()
        // 初始化 Bugly
        Bugly.init(this)
    }

    // ===========
    // = 重写方法 =
    // ===========

    // 是否使用默认 Engine 配置
    open fun isEngineConfig(): Boolean = true

    // 获取 Bugly 配置
    open fun getBuglyConfig(): BuglyConfig? = defaultBuglyConfig()

    // =============
    // = 初始化方法 =
    // =============

    /**
     * 初始化 DevAssist Engine 代码
     */
    private fun initializeEngine() {
        DevImageEngine.setEngine(GlideEngineImpl())
        DevJSONEngine.setEngine(GsonEngineImpl())
        DevPermissionEngine.setEngine(DevPermissionEngineImpl())
        DevMediaEngine.setEngine(PictureSelectorEngineImpl())
        DevCompressEngine.setEngine(LubanEngineImpl())
        DevLogEngine.setEngine(object : DevLoggerEngineImpl() {
            override fun isPrintLog(): Boolean = AppDebug.isOpenDebug()
        })
    }

    /**
     * Dev 系列工具类日志拦截
     */
    private fun devLogInterceptor() {
        // 可进行日志拦截编码
        //DevLogger.setPrint(DevLogger.Print());
        //JCLogUtils.setPrint(JCLogUtils.Print());
        LogPrintUtils.setPrint(LogPrintUtils.Print { logType, tag, message -> // 防止 null 处理
            var message: String? = message ?: return@Print
            // 进行编码处理
            message = StringUtils.strEncode(message, "UTF-8")
            when (logType) {
                Log.VERBOSE -> Log.v(tag, message)
                Log.DEBUG -> Log.d(tag, message)
                Log.INFO -> Log.i(tag, message)
                Log.WARN -> Log.w(tag, message)
                Log.ERROR -> Log.e(tag, message)
                Log.ASSERT -> Log.wtf(tag, message)
                else -> Log.wtf(tag, message)
            }
        })
    }
}
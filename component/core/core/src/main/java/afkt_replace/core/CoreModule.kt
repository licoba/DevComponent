package afkt_replace.core

import afkt_replace.core.app.AppDebug
import afkt_replace.core.app.AppEnvironment
import afkt_replace.core.lib.config.AppConst
import android.content.Context
import android.util.Log
import dev.DevUtils
import dev.engine.DevEngine
import dev.utils.DevFinal
import dev.utils.LogPrintUtils
import dev.utils.app.logger.DevLogger
import dev.utils.app.logger.LogConfig
import dev.utils.app.logger.LogLevel
import dev.utils.common.StringUtils

/**
 * detail: Core Module ( ContentProvider Initializer )
 * @author Ttt
 */
class CoreModule private constructor() : BaseModule(CoreModule::class.java.simpleName) {

    companion object {
        val instance: CoreModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CoreModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
        // 初始化 DevUtils
        DevUtils.init(context.applicationContext)
        // 初始化 DevUtils debug 相关配置
        if (AppDebug.isOpenDebug()) DevUtils.openDebug()
        if (AppDebug.isOpenLog()) DevUtils.openLog()

        // DevAssist Engine 初始化
        initializeEngine(context)
        // Dev 系列工具类初始化
        initializeDevUtils()

        // 初始化环境信息
        AppEnvironment.checker()
    }

    // ============
    // = 初始化方法 =
    // ============

    /**
     * DevAssist Engine 初始化
     */
    private fun initializeEngine(context: Context) {
        // 使用内部默认实现 Engine ( 使用 MMKV 必须调用 defaultMMKVInitialize() )
        DevEngine.defaultMMKVInitialize(context.applicationContext)
            .defaultEngine(DevEngine.getMMKVConfig())
    }

    /**
     * Dev 系列工具类初始化
     */
    private fun initializeDevUtils() {
        // 日志拦截 ( 方便调试 )
        if (AppDebug.isOpenLog()) {
            //DevLogger.setPrint(DevLogger.Print())
            //JCLogUtils.setPrint(JCLogUtils.Print())
            // 日志拦截, 进行编码处理
            LogPrintUtils.setPrint(LogPrintUtils.Print { logType, tag, message ->
                // 防止 null 处理
                if (message == null) return@Print
                // 编码处理
                val msg = StringUtils.strEncode(message, DevFinal.ENCODE.UTF_8)
                when (logType) {
                    Log.VERBOSE -> Log.v(tag, msg)
                    Log.DEBUG -> Log.d(tag, msg)
                    Log.INFO -> Log.i(tag, msg)
                    Log.WARN -> Log.w(tag, msg)
                    Log.ERROR -> Log.e(tag, msg)
                    Log.ASSERT -> Log.wtf(tag, msg)
                    else -> Log.wtf(tag, msg)
                }
            })
            // 初始化 Logger 配置
            DevLogger.initialize(LogConfig().apply {
                logLevel = LogLevel.DEBUG // 日志级别
                tag = AppConst.LOG_TAG
                methodCount = 0 // 堆栈方法总数 ( 显示经过的方法 )
                sortLog = true // 美化日志, 边框包围
            })
        } else {
            // 初始化 Logger 配置
            DevLogger.initialize(LogConfig().apply {
                logLevel = LogLevel.ERROR // 只打印 Error 级别日志
                tag = AppConst.LOG_TAG
            })
        }
    }
}
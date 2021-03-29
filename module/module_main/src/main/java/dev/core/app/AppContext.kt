package dev.core.app

import android.util.Log
import android.webkit.WebSettings
import com.alibaba.android.arouter.launcher.ARouter
import dev.DevUtils
import dev.core.assist.WebViewAssist
import dev.core.lib.base.BaseAppContext
import dev.core.lib.engine.compress.LubanEngineImpl
import dev.core.lib.engine.image.GlideEngineImpl
import dev.core.lib.engine.json.GsonEngineImpl
import dev.core.lib.engine.log.DevLoggerEngineImpl
import dev.core.lib.engine.media.PictureSelectorEngineImpl
import dev.core.lib.engine.permission.DevPermissionEngineImpl
import dev.core.property.BlockCanaryKT
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
import dev.utils.app.DeviceUtils
import dev.utils.app.PathUtils
import dev.utils.common.FileRecordUtils
import dev.utils.common.StringUtils

/**
 * detail: App Base Application
 * @author Ttt
 */
open class AppContext : BaseAppContext() {

    override fun onCreate() {
        super.onCreate()

        if (AppDebug.isOpenDebug()) {
            ARouter.openLog()
            ARouter.openDebug()
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace()
        }
        // 尽可能早, 推荐在 Application 中初始化
        ARouter.init(this)

        // 初始化 DevUtils debug 相关配置
        if (AppDebug.isOpenDebug()) DevUtils.openDebug()
        if (AppDebug.isOpenLog()) DevUtils.openLog()
        // 是否使用默认 Engine 配置
        if (isEngineConfig()) initializeEngine()
        // Dev 系列工具类初始化
        initializeDevUtils()
        // Bugly
        Bugly.init(this)
        // xCrash 提供捕获 java 崩溃、native 崩溃和 ANR 的能力, 不需要 root 权限或任何系统权限
        xcrash.XCrash.init(this)
        // BlockCanary
        BlockCanaryKT.init(this)
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
     * Dev 系列工具类初始化
     */
    private fun initializeDevUtils() {
        // 日志拦截 ( 方便调试 )
        if (AppDebug.isOpenLog()) {
            //DevLogger.setPrint(DevLogger.Print())
            //JCLogUtils.setPrint(JCLogUtils.Print())
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
        // 插入设备信息
        FileRecordUtils.setInsertInfo(DeviceUtils.getAppDeviceInfo())
        // 初始化 WebView 辅助类全局配置
        initializeWebViewBuilder()
    }

    /**
     * 初始化 WebView 辅助类全局配置
     */
    private fun initializeWebViewBuilder() {
        val builder: WebViewAssist.Builder = WebViewAssist.Builder()
            .setBuiltInZoomControls(true) // 显示内置缩放工具
            .setDisplayZoomControls(true) // 显示缩放工具
            .setAppCachePath( // Application Caches 地址
                PathUtils.getInternal().getAppCachePath("cache")
            )
            .setDatabasePath( // 数据库缓存路径
                PathUtils.getInternal().getAppCachePath("db")
            )
            .setRenderPriority(WebSettings.RenderPriority.HIGH) // 渲染优先级高
            .setOnApplyListener { _, _ ->
                DevLogEngine.getEngine().d("WebViewAssist Builder onApply")
            }
        // 基础布局算法
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            builder.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING)
        } else {
            builder.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        }
        WebViewAssist.setGlobalBuilder(builder)
    }
}
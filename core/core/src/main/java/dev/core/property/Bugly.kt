package dev.core.property

import android.os.Process
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import dev.core.AppContext
import dev.utils.app.AppUtils
import dev.utils.app.ProcessUtils

/**
 * detail: Bugly 初始化
 * @author Ttt
 */
object Bugly {

    /**
     * 初始化 Bugly
     * @param appContext [AppContext]
     */
    fun init(appContext: AppContext) {
        appContext.getBuglyConfig()?.let { config ->
            // 用户配置策略
            val strategy = UserStrategy(appContext)
            // 获取当前包名
            val packageName = appContext.packageName
            // 获取当前进程名
            val processName = ProcessUtils.getProcessName(Process.myPid())
            // 设置上报进程控制
            strategy.isUploadProcess = processName == null || processName == packageName
            // 设置 App 版本
            strategy.appVersion = AppUtils.getAppVersionName()
            // 设置 App 包名
            strategy.appPackageName = packageName
            // 延迟初始化 10s
            strategy.appReportDelay = 10000L
            // 设置渠道信息
            strategy.appChannel = config.channel
            // 初始化
            CrashReport.initCrashReport(
                appContext, config.key, config.debug, strategy
            )
        }
    }
}

/**
 * detail: Bugly 配置信息
 * @author Ttt
 */
class BuglyConfig(
    val key: String,
    val debug: Boolean,
    val channel: String
)
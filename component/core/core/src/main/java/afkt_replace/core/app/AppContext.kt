package afkt_replace.core.app

import afkt_replace.core.lib.base.BaseAppContext
import afkt_replace.core.lib.web.WebCoreLibrary
import afkt_replace.core.property.BlockCanaryKT
import afkt_replace.core.property.Bugly
import afkt_replace.core.property.BuglyConfig
import afkt_replace.core.property.defaultBuglyConfig
import com.alibaba.android.arouter.launcher.ARouter

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
        // 尽可能的早调用, 推荐在 Application 中初始化
        ARouter.init(this)

        // Bugly
        Bugly.init(this)
        // BlockCanary
        BlockCanaryKT.init(this)
        // 初始化 WebView 辅助类全局配置
        WebCoreLibrary.initializeWebViewBuilder()
    }

    // ==========
    // = 重写方法 =
    // ==========

    // 获取 Bugly 配置
    open fun getBuglyConfig(): BuglyConfig? = defaultBuglyConfig()
}
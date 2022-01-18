package afkt_replace.core.app

import afkt_replace.core.assist.WebViewAssist
import afkt_replace.core.lib.base.BaseAppContext
import afkt_replace.core.property.BlockCanaryKT
import afkt_replace.core.property.Bugly
import afkt_replace.core.property.BuglyConfig
import afkt_replace.core.property.defaultBuglyConfig
import android.webkit.WebSettings
import com.alibaba.android.arouter.launcher.ARouter
import dev.engine.DevEngine
import dev.utils.app.PathUtils

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
        initializeWebViewBuilder()
    }

    // ==========
    // = 重写方法 =
    // ==========

    // 获取 Bugly 配置
    open fun getBuglyConfig(): BuglyConfig? = defaultBuglyConfig()

    // ============
    // = 初始化方法 =
    // ============

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
            .setOnApplyListener(object : WebViewAssist.Builder.OnApplyListener {
                override fun onApply(
                    webViewAssist: WebViewAssist?,
                    builder: WebViewAssist.Builder
                ) {
                    DevEngine.getLog()?.apply {
                        d("WebViewAssist Builder onApply")
                    }
                }
            })
        // 基础布局算法
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            builder.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING)
        } else {
            builder.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        }
        WebViewAssist.setGlobalBuilder(builder)
    }
}
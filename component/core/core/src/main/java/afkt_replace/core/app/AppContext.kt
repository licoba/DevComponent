package afkt_replace.core.app

import afkt_replace.core.lib.base.core.AppDebug
import afkt_replace.core.lib.base.core.BaseAppContext
import afkt_replace.core.property.BlockCanaryKT
import afkt_replace.core.property.Bugly
import afkt_replace.core.property.BuglyConfig
import afkt_replace.core.property.defaultBuglyConfig
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import dev.DevUtils

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
    }

    // ==========
    // = 重写方法 =
    // ==========

    // 获取 Bugly 配置
    open fun getBuglyConfig(): BuglyConfig? = defaultBuglyConfig()

    // ==========
    // = 静态方法 =
    // ==========

    companion object {

        /**
         * 获取全局 Context
         * @return Context
         */
        fun content(): Context {
            return DevUtils.getContext()
        }

        /**
         * 获取 Context ( 判断 null, 视情况返回全局 Context)
         * @param context Context
         * @return Context
         */
        fun content(context: Context?): Context {
            return DevUtils.getContext(context)
        }
    }
}
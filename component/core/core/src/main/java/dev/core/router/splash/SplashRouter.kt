package dev.core.lib.config.main

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import dev.core.router.aaa.AAARouter.GROUP
import dev.core.router.main.MainRouter

/**
 * detail: Splash Module Router
 * @author Ttt
 */
object SplashRouter {

    const val GROUP = "splash"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_LAUNCHER = "/${GROUP}/launcher"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    fun build(path: String): Postcard {
        return ARouter.getInstance().build(path, GROUP)
    }

    fun buildAppMain(): Postcard {
        return ARouter.getInstance().build(MainRouter.PATH_MAIN, MainRouter.GROUP)
    }
}
package dev.core.router.aaa

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

/**
 * detail: AAA Module Router
 * @author Ttt
 */
object AAARouter {

    const val GROUP = "aaa"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_MAIN = "/$GROUP/main"

    // 对外公开 Fragment
    const val PATH_AAA_FRAGMENT = "/aaa/aaa/fragment"

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
}
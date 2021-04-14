package dev.core.lib.config

/**
 * detail: Core Route Path
 * @author Ttt
 * 跳转使用 ARouter.getInstance().build(path, group)
 * 为了明确表达出跳转的属于什么功能模块, 需要传入 group
 * 不使用 ARouter.getInstance().build(path) 跳转
 */
object CoreRoute {

    const val GROUP = "CORE"
}
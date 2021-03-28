package dev.core.property

import me.ele.uetool.UETool

/**
 * detail: UETool 调试工具
 * @author Ttt
 */
object UEToolKT {

    /**
     * 打开悬浮窗
     */
    fun showUETMenu(y: Int = 10) {
        UETool.showUETMenu(y)
    }

    /**
     * 关掉悬浮窗
     */
    fun dismissUETMenu() {
        UETool.dismissUETMenu()
    }
}
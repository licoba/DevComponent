package afkt_replace.core.app

import dev.core.BuildConfig

/**
 * detail: APP Debug、Release ( 区分 )
 * @author Ttt
 */
object AppDebug {

    /**
     * APP 是否 Release 模式
     */
    fun isRelease(): Boolean = !BuildConfig.openDebug

    /**
     * APP 是否 debug 模式
     */
    fun isOpenDebug(): Boolean = BuildConfig.openDebug

    /**
     * 是否开启日志
     */
    fun isOpenLog(): Boolean = BuildConfig.openLog

    /**
     * 是否编译调试工具
     */
    fun isDebugTools(): Boolean = BuildConfig.showDebugTools
}
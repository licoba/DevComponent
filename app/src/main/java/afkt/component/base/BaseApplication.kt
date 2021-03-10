package afkt.component.base

import dev.DevUtils
import dev.core.AppContext

/**
 * detail: Base Application
 * @author Ttt
 */
class BaseApplication : AppContext() {

    // ===========
    // = 重写方法 =
    // ===========

    override fun isAppDebug(): Boolean {
        return DevUtils.isDebug()
    }
}
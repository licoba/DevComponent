package afkt.component.base

import dev.DevUtils
import dev.core.AppContext
import dev.core.property.BuglyConfig

/**
 * detail: Base Application
 * @author Ttt
 */
class BaseApplication : AppContext() {

    // ===========
    // = 重写方法 =
    // ===========

    private var mBuglyConfig: BuglyConfig? = null

    override fun isAppDebug(): Boolean {
        return DevUtils.isDebug()
    }

    override fun getBuglyConfig(): BuglyConfig? {
        if (mBuglyConfig == null) {
            mBuglyConfig = BuglyConfig(
                key = (if (isAppDebug()) "" else ""),
                debug = isAppDebug(),
                channel = ""
            )
        }
        return mBuglyConfig
    }
}
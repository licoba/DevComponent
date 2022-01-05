package afkt_replace.core

import android.util.Log
import afkt_replace.core.app.AppDebug

/**
 * detail: Base Module ( ContentProvider Initializer )
 * @author Ttt
 * App Startup Initializer 基础 Module 泛型类, 方便统一初始化控制、打印日志等
 */
open class BaseModule(val TAG: String) {

    /**
     * 打印初始化日志
     */
    fun printInitialize() {
        if (AppDebug.isOpenDebug()) {
            Log.d(CoreConst.MODULAR_TAG, "$TAG - initialize")
        }
    }
}
package afkt_replace.core

/**
 * detail: Base Module ( ContentProvider Initializer )
 * @author Ttt
 * App Startup Initializer 基础 Module 类, 方便统一初始化控制、打印日志等
 */
open class BaseModule(val TAG: String) {

    init {
        CoreConst.printModularInitialize(TAG)
    }
}
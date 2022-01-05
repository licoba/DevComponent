package afkt_replace.module.aaa

import afkt_replace.core.BaseModule
import android.content.Context

/**
 * detail: AAA Module ( ContentProvider Initializer )
 * @author Ttt
 */
class AAAModule private constructor() : BaseModule(AAAModule::class.java.simpleName) {

    companion object {
        val instance: AAAModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AAAModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
        printInitialize()
    }
}
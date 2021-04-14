package afkt.module.aaa

import android.content.Context
import dev.core.BaseModule

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
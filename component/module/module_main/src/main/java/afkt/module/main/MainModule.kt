package afkt.module.main

import android.content.Context
import dev.core.BaseModule

/**
 * detail: Main Module ( ContentProvider Initializer )
 * @author Ttt
 */
class MainModule private constructor() : BaseModule(MainModule::class.java.simpleName) {

    companion object {
        val instance: MainModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MainModule()
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
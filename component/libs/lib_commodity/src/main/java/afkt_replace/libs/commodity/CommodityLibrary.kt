package afkt_replace.libs.commodity

import afkt_replace.core.BaseModule
import android.content.Context

/**
 * detail: Commodity Library ( ContentProvider Initializer )
 * @author Ttt
 */
class CommodityLibrary private constructor() : BaseModule(CommodityLibrary::class.java.simpleName) {

    companion object {
        val instance: CommodityLibrary by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CommodityLibrary()
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
package afkt_replace.libs.commodity

import android.content.Context
import afkt_replace.core.BaseModule

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
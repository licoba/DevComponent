package afkt_replace.module.commodity

import android.content.Context
import dev.core.BaseModule

/**
 * detail: Commodity Module ( ContentProvider Initializer )
 * @author Ttt
 */
class CommodityModule private constructor() : BaseModule(CommodityModule::class.java.simpleName) {

    companion object {
        val instance: CommodityModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CommodityModule()
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
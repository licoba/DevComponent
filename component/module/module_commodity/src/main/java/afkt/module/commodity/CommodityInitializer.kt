package afkt.module.commodity

import afkt_replace.libs.commodity.CommodityLibraryInitializer
import android.content.Context
import androidx.startup.Initializer
import afkt_replace.core.BaseInitializer

/**
 * detail: Commodity Module ( App Startup Initializer )
 * @author Ttt
 */
class CommodityInitializer : afkt_replace.core.BaseInitializer<CommodityModule>() {

    override fun create(context: Context): CommodityModule {
        CommodityModule.instance.initialize(context)
        return CommodityModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> {
        // 需要在 CommodityLibraryInitializer 初始化后才初始化该模块
        return mutableListOf(CommodityLibraryInitializer::class.java)
    }
}
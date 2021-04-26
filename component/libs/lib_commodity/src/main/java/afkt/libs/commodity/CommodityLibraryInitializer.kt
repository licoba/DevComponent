package afkt.libs.commodity

import android.content.Context
import androidx.startup.Initializer
import dev.core.BaseInitializer
import java.util.*

/**
 * detail: Commodity Library ( App Startup Initializer )
 * @author Ttt
 */
class CommodityLibraryInitializer : BaseInitializer<CommodityLibrary>() {

    override fun create(context: Context): CommodityLibrary {
        CommodityLibrary.instance.initialize(context)
        return CommodityLibrary.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}
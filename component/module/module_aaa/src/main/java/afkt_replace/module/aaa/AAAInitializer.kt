package afkt_replace.module.aaa

import android.content.Context
import androidx.startup.Initializer
import afkt_replace.core.BaseInitializer
import java.util.*

/**
 * detail: AAA Module ( App Startup Initializer )
 * @author Ttt
 */
class AAAInitializer : afkt_replace.core.BaseInitializer<AAAModule>() {

    override fun create(context: Context): AAAModule {
        AAAModule.instance.initialize(context)
        return AAAModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}
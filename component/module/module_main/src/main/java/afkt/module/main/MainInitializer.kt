package afkt.module.main

import android.content.Context
import androidx.startup.Initializer
import dev.core.BaseInitializer
import java.util.*

/**
 * detail: Main Module ( App Startup Initializer )
 * @author Ttt
 */
class MainInitializer : BaseInitializer<MainModule>() {

    override fun create(context: Context): MainModule {
        MainModule.instance.initialize(context)
        return MainModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}
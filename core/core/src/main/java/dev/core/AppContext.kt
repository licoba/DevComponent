package dev.core

import android.content.Context
import dev.DevUtils
import dev.core.app.AppChannel
import dev.core.lib.base.BaseAppContext
import dev.core.lib.engine.image.GlideEngineImpl
import dev.core.lib.engine.json.GsonEngineImpl
import dev.core.lib.engine.log.DevLoggerEngineImpl
import dev.core.lib.engine.permission.DevPermissionEngineImpl
import dev.core.property.Bugly
import dev.core.property.BuglyConfig
import dev.engine.image.DevImageEngine
import dev.engine.json.DevJSONEngine
import dev.engine.log.DevLogEngine
import dev.engine.permission.DevPermissionEngine

open class AppContext : BaseAppContext() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // 初始化 DevUtils
        DevUtils.init(base?.applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        // 是否使用默认 Engine 配置
        if (isEngineConfig()) {
            // 初始化 Engine
            DevImageEngine.setEngine(GlideEngineImpl())
            DevJSONEngine.setEngine(GsonEngineImpl())
            DevPermissionEngine.setEngine(DevPermissionEngineImpl())
            DevLogEngine.setEngine(object : DevLoggerEngineImpl() {
                override fun isPrintLog(): Boolean = isAppDebug()
            })
        }
        // 初始化 Bugly
        Bugly.init(this)
    }

    // ===========
    // = 重写方法 =
    // ===========

    // APP 是否 debug 模式
    open fun isAppDebug(): Boolean = false

    // 是否使用默认 Engine 配置
    open fun isEngineConfig(): Boolean = true

    // 获取 Bugly 配置
    open fun getBuglyConfig(): BuglyConfig? {
        return BuglyConfig(
            key = (if (isAppDebug()) Bugly.KEY_DEBUG else Bugly.KEY_RELEASE),
            debug = isAppDebug(),
            channel = AppChannel.getChannel()
        )
    }
}
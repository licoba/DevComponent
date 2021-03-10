package dev.core

import android.content.Context
import dev.DevUtils
import dev.core.lib.base.BaseAppContext
import dev.core.lib.engine.image.GlideEngineImpl
import dev.core.lib.engine.json.GsonEngineImpl
import dev.core.lib.engine.log.DevLoggerEngineImpl
import dev.core.lib.engine.permission.DevPermissionEngineImpl
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

        // 是否使用默认配置
        if (isDefaultConfig()) {
            // 初始化 Engine
            DevImageEngine.setEngine(GlideEngineImpl())
            DevJSONEngine.setEngine(GsonEngineImpl())
            DevPermissionEngine.setEngine(DevPermissionEngineImpl())
            DevLogEngine.setEngine(object : DevLoggerEngineImpl() {
                override fun isPrintLog(): Boolean = !DevUtils.isDebug()
            })
        }
    }

    open fun isDefaultConfig(): Boolean = true
}
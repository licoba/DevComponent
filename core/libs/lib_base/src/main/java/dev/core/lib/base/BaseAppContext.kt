package dev.core.lib.base

import android.content.Context
import androidx.multidex.MultiDexApplication
import dev.DevUtils

/**
 * detail: Base Application Context
 * @author Ttt
 */
open class BaseAppContext : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // 初始化 DevUtils
        DevUtils.init(base?.applicationContext)
    }
}
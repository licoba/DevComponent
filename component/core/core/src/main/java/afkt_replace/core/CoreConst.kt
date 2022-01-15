package afkt_replace.core

import afkt_replace.core.app.AppDebug
import android.util.Log

/**
 * detail: Core 模块常量
 * @author Ttt
 */
object CoreConst {

    // 初始化 TAG
    const val MODULAR_TAG = "Modular_Init"
    const val PROVIDER_TAG = "Provider_Init"

    /**
     * 打印 Modular 初始化日志
     * @param tag Module class Name
     */
    fun printModularInitialize(tag: String) {
        if (AppDebug.isOpenLog()) {
            Log.d(MODULAR_TAG, "$tag - initialize")
        }
    }

    /**
     * 打印 Provider 初始化日志
     * @param tag Module class Name
     */
    fun printProviderInitialize(tag: String) {
        if (AppDebug.isOpenLog()) {
            Log.d(PROVIDER_TAG, "$tag - initialize")
        }
    }
}
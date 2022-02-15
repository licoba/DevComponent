package afkt_replace.core.lib.utils.permission

import android.app.Activity
import android.content.Context
import dev.engine.DevEngine
import dev.engine.permission.IPermissionEngine

// =====================
// = IPermissionEngine =
// =====================

/**
 * 通过 Key 获取 Permission Engine
 * @param key String?
 * @return IPermissionEngine
 * 内部做了处理如果匹配不到则返回默认 Permission Engine
 */
internal fun getEngine(key: String?): IPermissionEngine {
    DevEngine.getPermission(key)?.let { engine ->
        return engine
    }
    return DevEngine.getPermission()
}

// =========================
// = Key Permission Engine =
// =========================

fun Context.isGranted(
    key: String? = null,
    vararg permissions: String?
): Boolean {
    return getEngine(key).isGranted(this, *permissions)
}

fun Activity.shouldShowRequestPermissionRationale(
    key: String? = null,
    vararg permissions: String?
): Boolean {
    return getEngine(key).shouldShowRequestPermissionRationale(
        this, *permissions
    )
}

fun Activity.getDeniedPermissionStatus(
    key: String? = null,
    shouldShow: Boolean,
    vararg permissions: String?
): MutableList<String> {
    return getEngine(key).getDeniedPermissionStatus(
        this, shouldShow, *permissions
    )
}

fun Activity.againRequest(
    key: String? = null,
    callback: IPermissionEngine.Callback?,
    deniedList: MutableList<String>?
): Int {
    return getEngine(key).againRequest(this, callback, deniedList)
}

// =============
// = 权限请求方法 =
// =============

fun Activity.request(
    key: String? = null,
    permissions: Array<out String>?
) {
    return getEngine(key).request(this, permissions)
}

fun Activity.request(
    key: String? = null,
    permissions: Array<out String>?,
    callback: IPermissionEngine.Callback?
) {
    return getEngine(key).request(this, permissions, callback)
}

fun Activity.request(
    key: String? = null,
    permissions: Array<out String>?,
    callback: IPermissionEngine.Callback?,
    againRequest: Boolean
) {
    return getEngine(key).request(this, permissions, callback, againRequest)
}
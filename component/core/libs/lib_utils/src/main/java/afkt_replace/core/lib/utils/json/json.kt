package afkt_replace.core.lib.utils.json

import dev.engine.DevEngine
import dev.engine.json.IJSONEngine
import dev.engine.json.JSONConfig
import java.lang.reflect.Type

// ===========================================
// = IJSONEngine<dev.engine.json.JSONConfig> =
// ===========================================

/**
 * 通过 Key 获取 JSON Engine
 * @param key String?
 * @return IJSONEngine<IJSONEngine.EngineConfig>
 * 内部做了处理如果匹配不到则返回默认 JSON Engine
 */
internal fun getEngine(key: String?): IJSONEngine<in IJSONEngine.EngineConfig>? {
    DevEngine.getJSON(key)?.let { engine ->
        return engine
    }
    return DevEngine.getJSON()
}

// ===================
// = Key JSON Engine =
// ===================

fun Any.toJson(key: String? = null): String? {
    return getEngine(key)?.toJson(this)
}

fun Any.toJson(
    key: String? = null,
    config: JSONConfig?
): String? {
    return getEngine(key)?.toJson(this, config)
}

// =

fun <T : Any> String.fromJson(
    key: String? = null,
    classOfT: Class<T>
): T? {
    return getEngine(key)?.fromJson(this, classOfT)
}

fun <T : Any> String.fromJson(
    key: String? = null,
    classOfT: Class<T>,
    config: JSONConfig?
): T? {
    return getEngine(key)?.fromJson(this, classOfT, config)
}

fun <T : Any> String.fromJson(
    key: String? = null,
    typeOfT: Type
): T? {
    return getEngine(key)?.fromJson(this, typeOfT)
}

fun <T : Any> String.fromJson(
    key: String? = null,
    typeOfT: Type,
    config: JSONConfig?
): T? {
    return getEngine(key)?.fromJson(this, typeOfT, config)
}

// ==========
// = 其他方法 =
// ==========

fun String.isJSON(key: String? = null): Boolean {
    return getEngine(key)?.isJSON(this) == true
}

fun String.isJSONObject(key: String? = null): Boolean {
    return getEngine(key)?.isJSONObject(this) == true
}

fun String.isJSONArray(key: String? = null): Boolean {
    return getEngine(key)?.isJSONArray(this) == true
}

fun Any.toJsonIndent(key: String? = null): String? {
    return getEngine(key)?.toJsonIndent(this)
}

fun Any.toJsonIndent(
    key: String? = null,
    config: JSONConfig?
): String? {
    return getEngine(key)?.toJsonIndent(this, config)
}
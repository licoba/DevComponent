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
    DevEngine.getJSON(key)?.let { value ->
        return value
    }
    return DevEngine.getJSON()
}

// ===================
// = Key JSON Engine =
// ===================

fun Any.toJson(engine: String? = null): String? {
    return getEngine(engine)?.toJson(this)
}

fun Any.toJson(
    engine: String? = null,
    config: JSONConfig?
): String? {
    return getEngine(engine)?.toJson(this, config)
}

// =

fun <T : Any> String.fromJson(
    engine: String? = null,
    classOfT: Class<T>?
): T? {
    return getEngine(engine)?.fromJson(this, classOfT)
}

fun <T : Any> String.fromJson(
    engine: String? = null,
    classOfT: Class<T>?,
    config: JSONConfig?
): T? {
    return getEngine(engine)?.fromJson(this, classOfT, config)
}

fun <T : Any> String.fromJson(
    engine: String? = null,
    typeOfT: Type?
): T? {
    return getEngine(engine)?.fromJson(this, typeOfT)
}

fun <T : Any> String.fromJson(
    engine: String? = null,
    typeOfT: Type?,
    config: JSONConfig?
): T? {
    return getEngine(engine)?.fromJson(this, typeOfT, config)
}

// ==========
// = 其他方法 =
// ==========

fun String.isJSON(engine: String? = null): Boolean {
    return getEngine(engine)?.isJSON(this) == true
}

fun String.isJSONObject(engine: String? = null): Boolean {
    return getEngine(engine)?.isJSONObject(this) == true
}

fun String.isJSONArray(engine: String? = null): Boolean {
    return getEngine(engine)?.isJSONArray(this) == true
}

fun Any.toJsonIndent(engine: String? = null): String? {
    return getEngine(engine)?.toJsonIndent(this)
}

fun Any.toJsonIndent(
    engine: String? = null,
    config: JSONConfig?
): String? {
    return getEngine(engine)?.toJsonIndent(this, config)
}
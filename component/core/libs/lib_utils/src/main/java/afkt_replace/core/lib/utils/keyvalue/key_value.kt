package afkt_replace.core.lib.utils.keyvalue

import dev.engine.DevEngine
import dev.engine.keyvalue.IKeyValueEngine
import dev.utils.DevFinal
import java.lang.reflect.Type

// =========================================================
// = IKeyValueEngine<extends IKeyValueEngine.EngineConfig> =
// =========================================================

/**
 * 通过 Key 获取 KeyValue Engine
 * @param engine String?
 * @return IKeyValueEngine<IKeyValueEngine.EngineConfig>
 * 内部做了处理如果匹配不到则返回默认 KeyValue Engine
 */
internal fun getEngine(engine: String?): IKeyValueEngine<in IKeyValueEngine.EngineConfig>? {
    DevEngine.getKeyValue(engine)?.let { value ->
        return value
    }
    return DevEngine.getKeyValue()
}

// ========
// = 默认值 =
// ========

private const val INTEGER_DEFAULT: Int = DevFinal.DEFAULT.INT
private const val LONG_DEFAULT: Long = DevFinal.DEFAULT.LONG
private const val FLOAT_DEFAULT: Float = DevFinal.DEFAULT.FLOAT
private const val DOUBLE_DEFAULT: Double = DevFinal.DEFAULT.DOUBLE
private const val BOOLEAN_DEFAULT: Boolean = DevFinal.DEFAULT.BOOLEAN

// =======================
// = Key KeyValue Engine =
// =======================

fun <T : IKeyValueEngine.EngineConfig> kv_getConfig(
    engine: String? = null
): T? {
    return getEngine(engine)?.config as? T
}

fun String.kv_remove(
    engine: String? = null
) {
    getEngine(engine)?.remove(this)
}

fun kv_removeForKeys(
    engine: String? = null,
    keys: Array<out String>?
) {
    getEngine(engine)?.removeForKeys(keys)
}

fun String.kv_contains(
    engine: String? = null
): Boolean {
    return getEngine(engine)?.contains(this) == true
}

fun kv_clear(
    engine: String? = null
) {
    getEngine(engine)?.clear()
}

fun String.kv_putInt(
    engine: String? = null,
    value: Int
): Boolean {
    return getEngine(engine)?.putInt(this, value) == true
}

fun String.kv_putLong(
    engine: String? = null,
    value: Long
): Boolean {
    return getEngine(engine)?.putLong(this, value) == true
}

fun String.kv_putFloat(
    engine: String? = null,
    value: Float
): Boolean {
    return getEngine(engine)?.putFloat(this, value) == true
}

fun String.kv_putDouble(
    engine: String? = null,
    value: Double
): Boolean {
    return getEngine(engine)?.putDouble(this, value) == true
}

fun String.kv_putBoolean(
    engine: String? = null,
    value: Boolean
): Boolean {
    return getEngine(engine)?.putBoolean(this, value) == true
}

fun String.kv_putString(
    engine: String? = null,
    value: String?
): Boolean {
    return getEngine(engine)?.putString(this, value) == true
}

fun <T : Any> String.kv_putEntity(
    engine: String? = null,
    value: T
): Boolean {
    return getEngine(engine)?.putEntity(this, value) == true
}

fun String.kv_getInt(
    engine: String? = null
): Int {
    return getEngine(engine)?.getInt(this) ?: INTEGER_DEFAULT
}

fun String.kv_getInt(
    engine: String? = null,
    defaultValue: Int
): Int {
    return getEngine(engine)?.getInt(this, defaultValue) ?: INTEGER_DEFAULT
}

fun String.kv_getLong(
    engine: String? = null
): Long {
    return getEngine(engine)?.getLong(this) ?: LONG_DEFAULT
}

fun String.kv_getLong(
    engine: String? = null,
    defaultValue: Long
): Long {
    return getEngine(engine)?.getLong(this, defaultValue) ?: LONG_DEFAULT
}

fun String.kv_getFloat(
    engine: String? = null
): Float {
    return getEngine(engine)?.getFloat(this) ?: FLOAT_DEFAULT
}

fun String.kv_getFloat(
    engine: String? = null,
    defaultValue: Float
): Float {
    return getEngine(engine)?.getFloat(this, defaultValue) ?: FLOAT_DEFAULT
}

fun String.kv_getDouble(
    engine: String? = null
): Double {
    return getEngine(engine)?.getDouble(this) ?: DOUBLE_DEFAULT
}

fun String.kv_getDouble(
    engine: String? = null,
    defaultValue: Double
): Double {
    return getEngine(engine)?.getDouble(this, defaultValue) ?: DOUBLE_DEFAULT
}

fun String.kv_getBoolean(
    engine: String? = null
): Boolean {
    return getEngine(engine)?.getBoolean(this) ?: BOOLEAN_DEFAULT
}

fun String.kv_getBoolean(
    engine: String? = null,
    defaultValue: Boolean
): Boolean {
    return getEngine(engine)?.getBoolean(this, defaultValue) ?: BOOLEAN_DEFAULT
}

fun String.kv_getString(
    engine: String? = null
): String? {
    return getEngine(engine)?.getString(this)
}

fun String.kv_getString(
    engine: String? = null,
    defaultValue: String?
): String? {
    return getEngine(engine)?.getString(this, defaultValue)
}

fun <T : Any> String.kv_getEntity(
    engine: String? = null,
    typeOfT: Type?
): T? {
    return getEngine(engine)?.getEntity(this, typeOfT) as? T
}

fun <T : Any> String.kv_getEntity(
    engine: String? = null,
    typeOfT: Type?,
    defaultValue: T?
): T? {
    return getEngine(engine)?.getEntity(this, typeOfT, defaultValue)
}
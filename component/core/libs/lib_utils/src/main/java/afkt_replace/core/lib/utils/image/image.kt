package afkt_replace.core.lib.utils.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import dev.base.DevSource
import dev.engine.DevEngine
import dev.engine.image.IImageEngine
import dev.engine.image.ImageConfig
import dev.engine.image.listener.LoadListener
import dev.engine.image.listener.OnConvertListener

// ==============================================
// = IImageEngine<dev.engine.image.ImageConfig> =
// ==============================================

/**
 * 通过 Key 获取 Image Engine
 * @param key String?
 * @return IImageEngine<IImageEngine.EngineConfig>
 * 内部做了处理如果匹配不到则返回默认 Image Engine
 */
internal fun getEngine(key: String?): IImageEngine<in IImageEngine.EngineConfig>? {
    DevEngine.getImage(key)?.let { engine ->
        return engine
    }
    return DevEngine.getImage()
}

// ==========
// = 快捷方法 =
// ==========

/**
 * 请求校验 Source
 * @param source DevSource?
 * @return `true` 直接 return, `false` 表示可以接着操作
 */
internal fun requireSource(source: DevSource?): Boolean {
    if (source?.isSource == true) {
        return false
    }
    return true
}

// ====================
// = Key Image Engine =
// ====================

// ====================
// = pause and resume =
// ====================

fun Fragment.pause(key: String? = null) {
    getEngine(key)?.pause(this)
}

fun Fragment.resume(key: String? = null) {
    getEngine(key)?.resume(this)
}

fun Context.pause(key: String? = null) {
    getEngine(key)?.pause(this)
}

fun Context.resume(key: String? = null) {
    getEngine(key)?.resume(this)
}

// ===========
// = preload =
// ===========

fun Context.preload(
    key: String? = null,
    source: DevSource?
) {
    if (requireSource(source)) return
    getEngine(key)?.preload(this, source)
}

fun Context.preload(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?
) {
    if (requireSource(source)) return
    getEngine(key)?.preload(this, source, config)
}

// =========
// = clear =
// =========

fun View.clear(key: String? = null) {
    getEngine(key)?.clear(this)
}

fun Fragment.clear(
    key: String? = null,
    view: View?
) {
    getEngine(key)?.clear(this, view)
}

fun Context.clearDiskCache(key: String? = null) {
    getEngine(key)?.clearDiskCache(this)
}

fun Context.clearMemoryCache(key: String? = null) {
    getEngine(key)?.clearMemoryCache(this)
}

fun Context.clearAllCache(key: String? = null) {
    getEngine(key)?.clearAllCache(this)
}

// =========
// = other =
// =========

fun Context.lowMemory(key: String? = null) {
    getEngine(key)?.lowMemory(this)
}

// ===========
// = display =
// ===========

fun ImageView.display(
    key: String? = null,
    source: DevSource?
) {
    if (requireSource(source)) return
    getEngine(key)?.display(this, source)
}

fun ImageView.display(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?
) {
    if (requireSource(source)) return
    getEngine(key)?.display(this, source, config)
}

fun <T : Any> ImageView.display(
    key: String? = null,
    source: DevSource?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(key)?.display(this, source, listener)
}

fun <T : Any> ImageView.display(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(key)?.display(this, source, config, listener)
}

// =

fun ImageView.display(
    key: String? = null,
    fragment: Fragment?,
    source: DevSource?
) {
    if (requireSource(source)) return
    getEngine(key)?.display(fragment, this, source)
}

fun ImageView.display(
    key: String? = null,
    fragment: Fragment?,
    source: DevSource?,
    config: ImageConfig?
) {
    if (requireSource(source)) return
    getEngine(key)?.display(fragment, this, source, config)
}

fun <T : Any> ImageView.display(
    key: String? = null,
    fragment: Fragment?,
    source: DevSource?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(key)?.display(fragment, this, source, listener)
}

fun <T : Any> ImageView.display(
    key: String? = null,
    fragment: Fragment?,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(key)?.display(fragment, this, source, config, listener)
}

// ========
// = load =
// ========

fun <T : Any> Context.loadImage(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(key)?.loadImage(this, source, config, listener)
}

fun <T : Any> Fragment.loadImage(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(key)?.loadImage(this, source, config, listener)
}

fun <T : Any> Context.loadImage(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    type: Class<*>?
): T? {
    if (requireSource(source)) return null
    return getEngine(key)?.loadImage(this, source, config, type)
}

fun <T : Any> Context.loadImageThrows(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    type: Class<*>?
): T? {
    if (requireSource(source)) return null
    return getEngine(key)?.loadImageThrows(this, source, config, type)
}

// =

fun Context.loadBitmap(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<Bitmap>?
) {
    if (requireSource(source)) return
    getEngine(key)?.loadBitmap(this, source, config, listener)
}

fun Fragment.loadBitmap(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<Bitmap>?
) {
    if (requireSource(source)) return
    getEngine(key)?.loadBitmap(this, source, config, listener)
}

fun Context.loadBitmap(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?
): Bitmap? {
    if (requireSource(source)) return null
    return getEngine(key)?.loadBitmap(this, source, config)
}

fun Context.loadBitmapThrows(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?
): Bitmap? {
    if (requireSource(source)) return null
    return getEngine(key)?.loadBitmapThrows(this, source, config)
}

// =

fun Context.loadDrawable(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<Drawable>?
) {
    if (requireSource(source)) return
    getEngine(key)?.loadDrawable(this, source, config, listener)
}

fun Fragment.loadDrawable(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<Drawable>?
) {
    if (requireSource(source)) return
    getEngine(key)?.loadDrawable(this, source, config, listener)
}

fun Context.loadDrawable(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?
): Drawable? {
    if (requireSource(source)) return null
    return getEngine(key)?.loadDrawable(this, source, config)
}

fun Context.loadDrawableThrows(
    key: String? = null,
    source: DevSource?,
    config: ImageConfig?
): Drawable? {
    if (requireSource(source)) return null
    return getEngine(key)?.loadDrawableThrows(this, source, config)
}

// ===========
// = convert =
// ===========

fun Context.convertImageFormat(
    key: String? = null,
    sources: MutableList<DevSource>?,
    listener: OnConvertListener?
): Boolean {
    return getEngine(key)?.convertImageFormat(this, sources, listener) ?: false
}

fun Context.convertImageFormat(
    key: String? = null,
    sources: MutableList<DevSource>?,
    config: ImageConfig?,
    listener: OnConvertListener?
): Boolean {
    return getEngine(key)?.convertImageFormat(this, sources, config, listener) ?: false
}
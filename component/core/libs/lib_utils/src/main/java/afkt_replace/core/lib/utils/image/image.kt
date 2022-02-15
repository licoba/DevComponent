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
 * @param engine String?
 * @return IImageEngine<IImageEngine.EngineConfig>
 * 内部做了处理如果匹配不到则返回默认 Image Engine
 */
internal fun getEngine(engine: String?): IImageEngine<in IImageEngine.EngineConfig>? {
    DevEngine.getImage(engine)?.let { value ->
        return value
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

fun Fragment.pause(
    engine: String? = null
) {
    getEngine(engine)?.pause(this)
}

fun Fragment.resume(
    engine: String? = null
) {
    getEngine(engine)?.resume(this)
}

fun Context.pause(
    engine: String? = null
) {
    getEngine(engine)?.pause(this)
}

fun Context.resume(
    engine: String? = null
) {
    getEngine(engine)?.resume(this)
}

// ===========
// = preload =
// ===========

fun Context.preload(
    engine: String? = null,
    source: DevSource?
) {
    if (requireSource(source)) return
    getEngine(engine)?.preload(this, source)
}

fun Context.preload(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?
) {
    if (requireSource(source)) return
    getEngine(engine)?.preload(this, source, config)
}

// =========
// = clear =
// =========

fun View.clear(
    engine: String? = null
) {
    getEngine(engine)?.clear(this)
}

fun Fragment.clear(
    engine: String? = null,
    view: View?
) {
    getEngine(engine)?.clear(this, view)
}

fun Context.clearDiskCache(
    engine: String? = null
) {
    getEngine(engine)?.clearDiskCache(this)
}

fun Context.clearMemoryCache(
    engine: String? = null
) {
    getEngine(engine)?.clearMemoryCache(this)
}

fun Context.clearAllCache(
    engine: String? = null
) {
    getEngine(engine)?.clearAllCache(this)
}

// =========
// = other =
// =========

fun Context.lowMemory(
    engine: String? = null
) {
    getEngine(engine)?.lowMemory(this)
}

// ===========
// = display =
// ===========

fun ImageView.display(
    engine: String? = null,
    source: DevSource?
) {
    if (requireSource(source)) return
    getEngine(engine)?.display(this, source)
}

fun ImageView.display(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?
) {
    if (requireSource(source)) return
    getEngine(engine)?.display(this, source, config)
}

fun <T : Any> ImageView.display(
    engine: String? = null,
    source: DevSource?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.display(this, source, listener)
}

fun <T : Any> ImageView.display(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.display(this, source, config, listener)
}

// =

fun ImageView.display(
    engine: String? = null,
    fragment: Fragment?,
    source: DevSource?
) {
    if (requireSource(source)) return
    getEngine(engine)?.display(fragment, this, source)
}

fun ImageView.display(
    engine: String? = null,
    fragment: Fragment?,
    source: DevSource?,
    config: ImageConfig?
) {
    if (requireSource(source)) return
    getEngine(engine)?.display(fragment, this, source, config)
}

fun <T : Any> ImageView.display(
    engine: String? = null,
    fragment: Fragment?,
    source: DevSource?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.display(fragment, this, source, listener)
}

fun <T : Any> ImageView.display(
    engine: String? = null,
    fragment: Fragment?,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.display(fragment, this, source, config, listener)
}

// ========
// = load =
// ========

fun <T : Any> Context.loadImage(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.loadImage(this, source, config, listener)
}

fun <T : Any> Fragment.loadImage(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<T>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.loadImage(this, source, config, listener)
}

fun <T : Any> Context.loadImage(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    type: Class<*>?
): T? {
    if (requireSource(source)) return null
    return getEngine(engine)?.loadImage(this, source, config, type)
}

fun <T : Any> Context.loadImageThrows(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    type: Class<*>?
): T? {
    if (requireSource(source)) return null
    return getEngine(engine)?.loadImageThrows(this, source, config, type)
}

// =

fun Context.loadBitmap(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<Bitmap>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.loadBitmap(this, source, config, listener)
}

fun Fragment.loadBitmap(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<Bitmap>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.loadBitmap(this, source, config, listener)
}

fun Context.loadBitmap(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?
): Bitmap? {
    if (requireSource(source)) return null
    return getEngine(engine)?.loadBitmap(this, source, config)
}

fun Context.loadBitmapThrows(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?
): Bitmap? {
    if (requireSource(source)) return null
    return getEngine(engine)?.loadBitmapThrows(this, source, config)
}

// =

fun Context.loadDrawable(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<Drawable>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.loadDrawable(this, source, config, listener)
}

fun Fragment.loadDrawable(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?,
    listener: LoadListener<Drawable>?
) {
    if (requireSource(source)) return
    getEngine(engine)?.loadDrawable(this, source, config, listener)
}

fun Context.loadDrawable(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?
): Drawable? {
    if (requireSource(source)) return null
    return getEngine(engine)?.loadDrawable(this, source, config)
}

fun Context.loadDrawableThrows(
    engine: String? = null,
    source: DevSource?,
    config: ImageConfig?
): Drawable? {
    if (requireSource(source)) return null
    return getEngine(engine)?.loadDrawableThrows(this, source, config)
}

// ===========
// = convert =
// ===========

fun Context.convertImageFormat(
    engine: String? = null,
    sources: MutableList<DevSource>?,
    listener: OnConvertListener?
): Boolean {
    return getEngine(engine)?.convertImageFormat(this, sources, listener) ?: false
}

fun Context.convertImageFormat(
    engine: String? = null,
    sources: MutableList<DevSource>?,
    config: ImageConfig?,
    listener: OnConvertListener?
): Boolean {
    return getEngine(engine)?.convertImageFormat(this, sources, config, listener) ?: false
}
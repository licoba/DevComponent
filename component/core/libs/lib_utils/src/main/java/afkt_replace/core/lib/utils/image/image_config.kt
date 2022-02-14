package afkt_replace.core.lib.utils.image

import afkt_replace.core.lib.base.core.AppSize
import dev.base.DevVariable
import dev.engine.image.ImageConfig

// ================================
// = dev.engine.image.ImageConfig =
// ================================

private val ROUND = ImageConfig.create().apply {
    setTransform(ImageConfig.TRANSFORM_ROUNDED_CORNERS)
    setScaleType(ImageConfig.SCALE_NONE)
}

val DEFAULT_CROP = ImageConfig.create().apply {
    setScaleType(ImageConfig.SCALE_CENTER_CROP)
}

val DEFAULT_FIX = ImageConfig.create().apply {
    setScaleType(ImageConfig.SCALE_FIT_CENTER)
}

val ROUND_10 = ImageConfig.create(ROUND).apply {
    setRoundedCornersRadius(
        AppSize.dp2px(10F)
    )
}

val ROUND_CROP_10 = ImageConfig.create(ROUND_10).apply {
    setScaleType(ImageConfig.SCALE_CENTER_CROP)
}

val ROUND_FIX_10 = ImageConfig.create(ROUND_10).apply {
    setScaleType(ImageConfig.SCALE_FIT_CENTER)
}

val ROUND_3 = ImageConfig.create(ROUND).apply {
    setRoundedCornersRadius(
        AppSize.dp2px(3F)
    )
}

// ==========
// = 其他方式 =
// ==========

// ===========
// = Variable =
// ===========

// Image Engine ImageConfig 配置缓存
private val VAR_IMAGE_CONFIG = DevVariable<String, ImageConfig?>()

// KEY.toImageConfig() => ImageConfig
const val KEY_ROUND_3 = "KEY_ROUND_3"
const val KEY_ROUND_10 = "KEY_ROUND_10"

/**
 * 清空全部 ImageConfig 配置
 * 当适配值变动只需要 clear 即可
 */
fun clearImageConfigVAR() {
    VAR_IMAGE_CONFIG.clearVariables()
}

/**
 * 通过 Key 获取 ImageConfig
 * @return [ImageConfig]
 */
fun String.toImageConfig(): ImageConfig? {
    VAR_IMAGE_CONFIG.getVariableValue(this)?.let { config ->
        return config
    }
    val config = createImageConfig(this)
    config?.let { VAR_IMAGE_CONFIG.putVariable(this, it) }
    return config
}

/**
 * 通过 Key 创建 ImageConfig
 * @param key String
 * @return ImageConfig?
 * 新增 Key 需要进行添加判断
 */
internal fun createImageConfig(key: String): ImageConfig? {
    return when (key) {
        KEY_ROUND_3 -> {
            ImageConfig.create(ROUND).apply {
                setRoundedCornersRadius(
                    AppSize.dp2px(3F)
                )
            }
        }
        KEY_ROUND_10 -> {
            ImageConfig.create(ROUND).apply {
                // 为了获取最新适配值重新读取
                setRoundedCornersRadius(
                    AppSize.dp2px(10F)
                )
            }
        }
        else -> {
            null
        }
    }
}
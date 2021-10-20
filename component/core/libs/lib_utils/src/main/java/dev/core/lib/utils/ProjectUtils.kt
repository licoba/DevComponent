package dev.core.lib.utils

import dev.base.DevVariable
import dev.engine.image.ImageConfig
import dev.utils.app.SizeUtils

object ProjectUtils {

    // ==================
    // = DevImageEngine =
    // ==================

    // GlideConfig 配置变量
    private val sConfigVariable: DevVariable<Int, ImageConfig?> = DevVariable<Int, ImageConfig?>()

    /**
     * 获取圆角 GlideConfig
     * @return 圆角 [ImageConfig]
     */
    fun getRoundConfig3(): ImageConfig {
        return getRoundConfig(3)
    }

    /**
     * 获取圆角 GlideConfig
     * @return 圆角 [ImageConfig]
     */
    fun getRoundConfig10(): ImageConfig {
        return getRoundConfig(10)
    }

    /**
     * 获取圆角 GlideConfig
     * @param roundDP 圆角 dp 值
     * @return [ImageConfig]
     */
    fun getRoundConfig(roundDP: Int): ImageConfig {
        var config: ImageConfig? = sConfigVariable.getVariableValue(roundDP)
        if (config != null) return config
        config = ImageConfig.create()
        config.setRoundedCornersRadius(SizeUtils.dipConvertPx(roundDP.toFloat()))
        config.setTransform(ImageConfig.TRANSFORM_ROUNDED_CORNERS)
        sConfigVariable.putVariable(roundDP, config)
        return config
    }
}
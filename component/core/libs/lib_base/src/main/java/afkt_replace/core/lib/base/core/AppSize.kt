package afkt_replace.core.lib.base.core

import android.content.Context
import dev.DevUtils
import dev.base.DevVariableExt
import dev.utils.app.SizeUtils

/**
 * detail: App 适配值转换快捷类
 * @author Ttt
 * 该类主要用于内部缓存适配值, 便于第二次直接从缓存中读取
 * 也可使用
 * SizeUtils.dp2px(context, value).toFloat()
 * AutoSizeUtils.dp2px(context, value).toFloat()
 */
object AppSize {

    // =============
    // = 对外公开方法 =
    // =============

    val CONVERT = Convert()

    /**
     * dp 转 px
     * @param value 待转换值
     * @return 转换后的值
     */
    fun dp2px(value: Float): Int {
        return dp2px(null, value)
    }

    /**
     * dp 转 px ( float )
     * @param value 待转换值
     * @return 转换后的值
     */
    fun dp2pxf(value: Float): Float {
        return dp2pxf(null, value)
    }

    /**
     * px 转 dp
     * @param value 待转换值
     * @return 转换后的值
     */
    fun px2dp(value: Float): Int {
        return px2dp(null, value)
    }

    /**
     * px 转 dp ( float )
     * @param value 待转换值
     * @return 转换后的值
     */
    fun px2dpf(value: Float): Float {
        return px2dpf(null, value)
    }

    /**
     * sp 转 px
     * @param value 待转换值
     * @return 转换后的值
     */
    fun sp2px(value: Float): Int {
        return sp2px(null, value)
    }

    /**
     * sp 转 px ( float )
     * @param value 待转换值
     * @return 转换后的值
     */
    fun sp2pxf(value: Float): Float {
        return sp2pxf(null, value)
    }

    /**
     * px 转 sp
     * @param value 待转换值
     * @return 转换后的值
     */
    fun px2sp(value: Float): Int {
        return px2sp(null, value)
    }

    /**
     * px 转 sp ( float )
     * @param value 待转换值
     * @return 转换后的值
     */
    fun px2spf(value: Float): Float {
        return px2spf(null, value)
    }

    // ===========
    // = Context =
    // ===========

    /**
     * dp 转 px
     * @param context Context
     * @param value 待转换值
     * @return 转换后的值
     */
    fun dp2px(
        context: Context?,
        value: Float
    ): Int {
        return dp2pxf(context, value).toInt()
    }

    /**
     * dp 转 px ( float )
     * @param context Context
     * @param value 待转换值
     * @return 转换后的值
     */
    fun dp2pxf(
        context: Context?,
        value: Float
    ): Float {
        return CONVERT.dp2pxConvert.getVariableValue(
            value, context
        )
    }

    /**
     * px 转 dp
     * @param context Context
     * @param value 待转换值
     * @return 转换后的值
     */
    fun px2dp(
        context: Context?,
        value: Float
    ): Int {
        return px2dpf(context, value).toInt()
    }

    /**
     * px 转 dp ( float )
     * @param context Context
     * @param value 待转换值
     * @return 转换后的值
     */
    fun px2dpf(
        context: Context?,
        value: Float
    ): Float {
        return CONVERT.px2dpConvert.getVariableValue(
            value, context
        )
    }

    /**
     * sp 转 px
     * @param context Context
     * @param value 待转换值
     * @return 转换后的值
     */
    fun sp2px(
        context: Context?,
        value: Float
    ): Int {
        return sp2pxf(context, value).toInt()
    }

    /**
     * sp 转 px ( float )
     * @param context Context
     * @param value 待转换值
     * @return 转换后的值
     */
    fun sp2pxf(
        context: Context?,
        value: Float
    ): Float {
        return CONVERT.sp2pxConvert.getVariableValue(
            value, context
        )
    }

    /**
     * px 转 sp
     * @param context Context
     * @param value 待转换值
     * @return 转换后的值
     */
    fun px2sp(
        context: Context?,
        value: Float
    ): Int {
        return px2spf(context, value).toInt()
    }

    /**
     * px 转 sp ( float )
     * @param context Context
     * @param value 待转换值
     * @return 转换后的值
     */
    fun px2spf(
        context: Context?,
        value: Float
    ): Float {
        return CONVERT.px2spConvert.getVariableValue(
            value, context
        )
    }

    // =============
    // = 内部转换存储 =
    // =============

    /**
     * detail: 转换适配值封装
     * @author Ttt
     */
    class Convert {

        // dp 转 px ( float )
        val dp2pxConvert: DevVariableExt<Float, Float, Context?> by lazy {
            DevVariableExt<Float, Float, Context?> { key, context ->
                innerCreator(context, Type.dp2px, key)
            }
        }

        // px 转 dp ( float )
        val px2dpConvert: DevVariableExt<Float, Float, Context?> by lazy {
            DevVariableExt<Float, Float, Context?> { key, context ->
                innerCreator(context, Type.px2dp, key)
            }
        }

        // sp 转 px ( float )
        val sp2pxConvert: DevVariableExt<Float, Float, Context?> by lazy {
            DevVariableExt<Float, Float, Context?> { key, context ->
                innerCreator(context, Type.sp2px, key)
            }
        }

        // px 转 sp ( float )
        val px2spConvert: DevVariableExt<Float, Float, Context?> by lazy {
            DevVariableExt<Float, Float, Context?> { key, context ->
                innerCreator(context, Type.px2sp, key)
            }
        }

        // =

        private enum class Type {

            dp2px,

            px2dp,

            sp2px,

            px2sp
        }

        /**
         * 适配值内部创建实现
         * @param context Context
         * @param type Type
         * @param key Float
         * @return Float
         */
        private fun innerCreator(
            context: Context?,
            type: Type,
            key: Float
        ): Float {
            val ctx = context ?: DevUtils.getTopActivity()
            return when (type) {
                Type.dp2px -> {
                    SizeUtils.dp2pxf(
                        DevUtils.getContext(ctx), key
                    )
                }
                Type.px2dp -> {
                    SizeUtils.px2dpf(
                        DevUtils.getContext(ctx), key
                    )
                }
                Type.sp2px -> {
                    SizeUtils.sp2pxf(
                        DevUtils.getContext(ctx), key
                    )
                }
                Type.px2sp -> {
                    SizeUtils.px2spf(
                        DevUtils.getContext(ctx), key
                    )
                }
            }
        }

        /**
         * 适配值内部转换
         * @param context Context?
         * @param type Type
         * @param key Float
         * @return Float
         * 该方法主要解决出现转换失败返回 0 的情况
         * 导致后续获取缓存值为 0 直接返回使用
         */
        private fun innerConvert(
            context: Context?,
            type: Type,
            key: Float
        ): Float {
            when (type) {
                Type.dp2px -> {
                    val value: Float = dp2pxConvert.getVariableValue(key, context)
                    if (value == 0F) {
                        dp2pxConvert.variable.removeVariable(key)
                    }
                    return value
                }
                Type.px2dp -> {
                    val value: Float = px2dpConvert.getVariableValue(key, context)
                    if (value == 0F) {
                        px2dpConvert.variable.removeVariable(key)
                    }
                    return value
                }
                Type.sp2px -> {
                    val value: Float = sp2pxConvert.getVariableValue(key, context)
                    if (value == 0F) {
                        sp2pxConvert.variable.removeVariable(key)
                    }
                    return value
                }
                Type.px2sp -> {
                    val value: Float = px2spConvert.getVariableValue(key, context)
                    if (value == 0F) {
                        px2spConvert.variable.removeVariable(key)
                    }
                    return value
                }
            }
        }
    }
}
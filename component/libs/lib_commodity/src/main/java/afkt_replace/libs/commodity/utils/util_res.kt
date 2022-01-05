package afkt_replace.libs.commodity.utils

import afkt_replace.core.lib.bean.commodity.enums.CommodityLabel
import afkt_replace.libs.commodity.R
import android.graphics.drawable.Drawable
import dev.utils.app.ResourceUtils
import dev.utils.app.SpanUtils

/**
 * detail: 资源文件通用获取
 * @author Ttt
 */

fun getCommodityLabelDrawableId(type: CommodityLabel): Int {
    return getCommodityLabelDrawableId(type.type)
}

fun getCommodityLabelDrawable(type: CommodityLabel): Drawable? {
    val id = getCommodityLabelDrawableId(type.type)
    return if (id != 0) ResourceUtils.getDrawable(id) else null
}

fun getCommodityLabelDrawable(type: Int): Drawable? {
    val id = getCommodityLabelDrawableId(type)
    return if (id != 0) ResourceUtils.getDrawable(id) else null
}

fun getCommodityLabelDrawableId(type: Int): Int {
    return when (type) {
        100 -> R.drawable.lib_commodity_images_label_advance_sale
        101 -> R.drawable.lib_commodity_images_label_coupon
        102 -> R.drawable.lib_commodity_images_label_full_reduction
        103 -> R.drawable.lib_commodity_images_label_group
        104 -> R.drawable.lib_commodity_images_label_new
        105 -> R.drawable.lib_commodity_images_label_promotion
        106 -> R.drawable.lib_commodity_images_label_seckill
        107 -> R.drawable.lib_commodity_images_label_topic
        else -> 0
    }
}

/**
 * 追加 Label
 * @param name 商品名
 * @param type 商品类型
 * @return SpanUtils
 */
fun appendLabel(
    name: String,
    type: Int
): SpanUtils {
    // 拼接标题
    val span = SpanUtils()
    val labelICON = getCommodityLabelDrawable(type)
    if (labelICON != null) {
        span.appendImage(labelICON, SpanUtils.ALIGN_CENTER).append(" ")
    }
    span.append(name)
    return span
}
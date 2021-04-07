package afkt.libs.commodity.enums

import afkt.libs.commodity.R
import android.graphics.drawable.Drawable
import dev.utils.app.ResourceUtils

/**
 * detail: 商品 label icon
 * @author Ttt
 */
enum class CommodityLabel(
    val type: Int
) {
    // 未知
    NONE(0),

    // 预售
    ADVANCE_SALE(100),

    // 优惠券
    COUPON(101),

    // 满减
    FULL_REDUCTION(102),

    // 团购
    GROUP(103),

    // 上新
    NEW(104),

    // 促销
    PROMOTION(105),

    // 秒杀
    SECKILL(106),

    // 专题
    TOPIC(107),

    ;

    companion object {

        fun get(type: Int): CommodityLabel {
            return when (type) {
                100 -> ADVANCE_SALE
                101 -> COUPON
                102 -> FULL_REDUCTION
                103 -> GROUP
                104 -> NEW
                105 -> PROMOTION
                106 -> SECKILL
                107 -> TOPIC
                else -> NONE
            }
        }

        fun getDrawable(type: Int): Drawable? {
            return get(type).getDrawable()
        }

        fun getDrawableId(type: Int): Int {
            return get(type).getDrawableId()
        }
    }

    fun getDrawable(): Drawable? {
        return getDrawable(type)
    }

    fun getDrawableId(): Int {
        return getDrawableId(type)
    }

    // ===========
    // = 内部方法 =
    // ===========

    private fun getDrawable(type: Int): Drawable? {
        val id = getDrawableId(type)
        return if (id != 0) ResourceUtils.getDrawable(id) else null
    }

    private fun getDrawableId(type: Int): Int {
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
}
package afkt.libs.commodity.enums

import afkt.libs.commodity.R
import android.graphics.drawable.Drawable
import dev.utils.app.ResourceUtils

/**
 * detail: 商品 type
 * @author Ttt
 */
enum class CommodityType(
    val type: Int
) {
    // 未知
    NONE(0),

    // 普通商品
    NORMAL(300),

    // 拼团商品
    GROUP(301),

    // 砍价商品
    BARGAIN(302),

    // 众筹商品
    CROWD_FUNDING(303),

    ;

    companion object {

        fun get(type: Int): CommodityType {
            return when (type) {
                300 -> NORMAL
                301 -> GROUP
                302 -> BARGAIN
                303 -> CROWD_FUNDING
                else -> NONE
            }
        }

        fun getDrawable(type: Int): Drawable? {
            return CommodityLabel.get(type).getDrawable()
        }

        fun getDrawableId(type: Int): Int {
            return CommodityLabel.get(type).getDrawableId()
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
            301 -> R.drawable.lib_commodity_images_type_group
            302 -> R.drawable.lib_commodity_images_type_bargain
            303 -> R.drawable.lib_commodity_images_type_crowd_funding
            else -> 0
        }
    }
}
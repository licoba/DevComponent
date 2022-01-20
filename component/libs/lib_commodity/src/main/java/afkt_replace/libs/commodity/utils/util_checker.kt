package afkt_replace.libs.commodity.utils

/**
 * detail: 通用判断
 * @author Ttt
 */

/**
 * 是否普通购物 ( 国内购物 )
 * @param type Int
 * @return `true` yes, `false` no
 */
fun isNormalPurchase(type: Int): Boolean {
    return type <= 0
}

/**
 * 是否海外购 ( 全球购 )
 * @param type Int
 * @return `true` yes, `false` no
 */
fun isGlobalPurchase(type: Int): Boolean {
    return type == 1
}
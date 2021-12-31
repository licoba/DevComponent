package afkt.libs.commodity.utils

import dev.utils.common.BigDecimalUtils
import dev.utils.common.NumberUtils
import java.math.BigDecimal

/**
 * detail: 通用价格转换
 * @author Ttt
 */

/**
 * 提供精确的小数位四舍五入处理
 * @param price 需要四舍五入的数值
 * @return 四舍五入后的结果
 */
fun roundPrice(price: Any): Double {
    val value = BigDecimalUtils.round(price, 2, BigDecimal.ROUND_DOWN)
    if (value == BigDecimalUtils.ERROR_VALUE) return 0.0
    return value
}

/**
 * 提供精确的小数位四舍五入处理
 * @param price 需要四舍五入的数值
 * @return 四舍五入后的结果
 */
fun roundPriceStr(price: Any): String {
    val value = BigDecimalUtils.round(price, 2, BigDecimal.ROUND_DOWN)
    if (value == BigDecimalUtils.ERROR_VALUE) return ""
    return NumberUtils.subZeroAndDot(value)
}
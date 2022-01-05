package afkt_replace.core.lib.bean.commodity.enums

// =============
// = 商品 label =
// =============

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
    }
}

// ==========
// = 列表类型 =
// ==========

/**
 * detail: 商品列表展示 type
 * @author Ttt
 */
enum class CommodityList(
    val type: Int
) {
    // 未知
    NONE(0),

    // TOP 100
    TOP_100_RANKING(200),

    // 利润排行榜
    PROFIT_RANKING(201),

    // 销量排行榜
    SALES_RANKING(202),

    // 新品首发列表
    NEW_LIST(203),

    // 拼团列表
    GROUP_LIST(204),

    // 砍价列表
    BARGAIN_LIST(205),

    // 秒杀列表
    SECKILL_LIST(206),

    // 专题列表
    TOPIC_LIST(207),

    // 众筹列表
    CROWD_FUNDING_LIST(208),

    ;

    companion object {

        fun get(type: Int): CommodityList {
            return when (type) {
                200 -> TOP_100_RANKING
                201 -> PROFIT_RANKING
                202 -> SALES_RANKING
                203 -> NEW_LIST
                204 -> GROUP_LIST
                205 -> BARGAIN_LIST
                206 -> SECKILL_LIST
                207 -> TOPIC_LIST
                208 -> CROWD_FUNDING_LIST
                else -> NONE
            }
        }
    }
}
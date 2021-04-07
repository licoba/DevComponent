package afkt.libs.commodity.enums

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
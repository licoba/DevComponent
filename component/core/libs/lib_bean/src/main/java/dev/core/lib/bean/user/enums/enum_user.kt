package dev.core.lib.bean.user.enums

/**
 * detail: 性别
 * @author Ttt
 */
enum class SexEnum(
    val value: Int
) {

    // 默认未设置
    NONE(0),

    // 男
    MALE(1),

    // 女
    FEMALE(2),

    ;

    companion object {

        fun get(value: Int): SexEnum {
            return when (value) {
                1 -> MALE
                2 -> FEMALE
                else -> NONE
            }
        }
    }
}

/**
 * detail: 用户状态
 * @author Ttt
 */
enum class UserStateEnum(
    val value: Int
) {

    // 转换失败异常状态
    ERROR(0),

    // 默认 ( 未认证 )
    NORMAL(1),

    // 已认证
    VERIFIED(2),

    // 账号已过期 ( 已失效 )
    EXPIRED(998),

    // 账号违规封禁
    BAN(999),

    ;

    companion object {

        fun get(value: Int): UserStateEnum {
            return when (value) {
                1 -> NORMAL
                2 -> VERIFIED
                998 -> EXPIRED
                999 -> BAN
                else -> ERROR
            }
        }
    }
}

/**
 * detail: 用户级别
 * @author Ttt
 */
enum class UserLevelEnum(
    val value: Int
) {

    // 普通用户
    NORMAL(0),

    // 黄金会员
    GOLD(1),

    // 铂金会员
    PT(2),

    // 钻石会员
    DIAMOND(3),

    ;

    companion object {

        fun get(value: Int): UserLevelEnum {
            return when (value) {
                1 -> GOLD
                2 -> PT
                3 -> DIAMOND
                else -> NORMAL
            }
        }
    }
}
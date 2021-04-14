package dev.core.lib.bean

import android.os.Parcelable
import dev.core.lib.config.AppConst
import dev.utils.common.ColorUtils
import kotlinx.android.parcel.Parcelize

/**
 * detail: 用户信息实体类
 * @author Ttt
 * 用户性别 [SexEnum]
 * 用户状态 [UserStateEnum]
 * 用户级别 [UserLevelEnum]
 */
@Parcelize
class UserInfo(
    var token: String?,
    var id: String?, // 唯一 id
    var nickName: String?,
    var account: String?,
    var introduction: String?,
    var avatar: String? = AppConst.AVATAR_URL,
    var background: String? = AppConst.USER_BACKGROUND_URL,
    var sex: Int = 0, // 用户性别
    var state: Int = 0, // 用户状态
    var level: Int = 0, // 用户级别
) : Parcelable

/**
 * detail: 自定义对象 ARouter 传递
 * @author Ttt
 */
open class AfkT() {

    var value: String? = null

    var color: Int = 0

    constructor(
        value: String?,
    ) : this(
        value,
        ColorUtils.setAlphaDark(ColorUtils.getRandomColor(), 100)
    )

    constructor(
        value: String?,
        color: Int
    ) : this() {
        this.value = value
        this.color = color
    }
}
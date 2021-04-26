package dev.core.lib.bean.user

import android.os.Parcelable
import dev.core.lib.bean.SexEnum
import dev.core.lib.bean.UserLevelEnum
import dev.core.lib.bean.UserStateEnum
import dev.core.lib.config.AppConst
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
package afkt_replace.core.lib.router.module.user

import afkt_replace.core.lib.bean.user.UserInfo
import afkt_replace.core.lib.router.BaseProvider

/**
 * detail: User 各个组件通讯接口
 * @author Ttt
 */
interface IUserProvider : BaseProvider {

    /**
     * 是否登录
     * @return `true` yes, `false` no
     */
    fun isLogin(): Boolean

    /**
     * 获取用户信息
     * @return [UserInfo]
     */
    fun getUserInfo(): UserInfo?
}
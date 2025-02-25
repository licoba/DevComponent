package afkt_replace.module.user

import afkt_replace.core.lib.bean.user.UserInfo
import afkt_replace.core.lib.bean.user.enums.SexEnum
import afkt_replace.core.lib.bean.user.enums.UserLevelEnum
import afkt_replace.core.lib.bean.user.enums.UserStateEnum
import afkt_replace.core.lib.config.AppConst
import afkt_replace.core.lib.router.BaseProviderExt
import afkt_replace.core.lib.router.module.user.IUserProvider
import afkt_replace.core.lib.router.module.user.UserRouter
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.common.ChineseUtils
import dev.utils.common.DevCommonUtils
import dev.utils.common.RandomUtils

@Route(path = UserRouter.PATH_USER_PROVIDER, group = UserRouter.GROUP)
class UserProvider : IUserProvider,
    BaseProviderExt(UserProvider::class.java.simpleName) {

    override fun init(context: Context?) {
        // 随机性创建
        if (RandomUtils.nextBoolean()) {
            // 可以通过读取配置获取等
            userInfo = UserInfo(
                token = DevCommonUtils.getRandomUUIDToString(),
                id = "${RandomUtils.getRandom(100000, 1000000)}",
                nickName = ChineseUtils.randomWord(3),
                account = "a123456",
                introduction = "这是简介",
                avatar = AppConst.AVATAR_URL,
                background = AppConst.USER_BACKGROUND_URL,
                sex = SexEnum.MALE.value,
                state = UserStateEnum.NORMAL.value,
                level = UserLevelEnum.PT.value,
            )
        }
    }

    override fun isLogin(): Boolean {
        return userInfo != null
    }

    override fun getUserInfo(): UserInfo? {
        return userInfo
    }

    // =

    private var userInfo: UserInfo? = null
}
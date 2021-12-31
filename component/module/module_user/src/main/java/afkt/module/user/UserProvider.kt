package afkt.module.user

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import dev.core.CoreConst
import dev.core.app.AppDebug
import dev.core.lib.bean.user.enums.SexEnum
import dev.core.lib.bean.user.enums.UserLevelEnum
import dev.core.lib.bean.user.enums.UserStateEnum
import dev.core.lib.bean.user.UserInfo
import dev.core.lib.config.AppConst
import dev.core.router.user.IUserProvider
import dev.core.router.user.UserRouter
import dev.utils.common.ChineseUtils
import dev.utils.common.DevCommonUtils
import dev.utils.common.RandomUtils

@Route(path = UserRouter.PATH_USER_PROVIDER, group = UserRouter.GROUP)
class UserProvider : IUserProvider {

    val TAG = UserProvider::class.java.simpleName

    override fun init(context: Context?) {
        if (AppDebug.isOpenDebug()) {
            Log.d(CoreConst.PROVIDER_TAG, "$TAG - initialize")
        }
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
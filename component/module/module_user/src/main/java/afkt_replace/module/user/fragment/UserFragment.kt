package afkt_replace.module.user.fragment

import afkt_replace.core.lib.base.app.BaseFragmentViewBinding
import afkt_replace.core.lib.bean.ThemeIntent
import afkt_replace.core.lib.router.module.user.UserNav
import afkt_replace.core.lib.router.module.user.UserRouter
import afkt_replace.core.lib.utils.image.ROUND_10
import afkt_replace.core.lib.utils.image.display
import afkt_replace.core.lib.utils.image.loadDrawable
import afkt_replace.core.lib.utils.toSource
import afkt_replace.module.user.R
import afkt_replace.module.user.databinding.UserFragmentBinding
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.base.DevSource
import dev.engine.DevEngine
import dev.engine.image.listener.DrawableListener
import dev.utils.DevFinal

@Route(path = UserRouter.PATH_USER_FRAGMENT, group = UserRouter.GROUP)
class UserFragment : BaseFragmentViewBinding<UserFragmentBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.DATA)
    var themeIntent: ThemeIntent? = null

    override fun baseLayoutId(): Int = R.layout.user_fragment

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        themeIntent?.let { uiController.setAllBackground(it.color) }

        UserNav.userProvider()?.apply {
            // 如果已经登录了
            if (isLogin()) {
                getUserInfo()?.let { user ->
                    binding.vidNicknameTv.text = user.nickName
                    // 加载用户图片
                    binding.vidAvatarIv.display(
                        source = user.avatar?.toSource(),
                        config = ROUND_10
                    )
                    // 加载背景
                    activity?.loadDrawable(
                        source = user.background?.toSource(),
                        config = null,
                        listener = object : DrawableListener() {
                            @SuppressLint("NewApi")
                            override fun onResponse(
                                source: DevSource?,
                                value: Drawable?
                            ) {
                                binding.vidContentTv.background = value
                            }

                            override fun onStart(source: DevSource?) {
                            }

                            override fun onFailure(
                                source: DevSource?,
                                throwable: Throwable?
                            ) {
                                DevEngine.getLog()?.eTag(TAG, throwable)
                            }
                        }
                    )
                }
            } else {
                binding.vidNicknameTv.text = "用户未登录"
            }
        }
    }
}
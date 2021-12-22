package afkt.module.user.fragment

import afkt.module.user.R
import afkt.module.user.databinding.UserFragmentBinding
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.base.DevSource
import dev.core.lib.base.app.BaseFragmentViewBinding
import dev.core.lib.bean.AfkT
import dev.core.lib.utils.ProjectUtils
import dev.core.router.user.UserRouter
import dev.engine.image.DevImageEngine
import dev.engine.image.listener.DrawableListener
import dev.engine.log.DevLogEngine
import dev.utils.DevFinal

@Route(path = UserRouter.PATH_USER_FRAGMENT, group = UserRouter.GROUP)
class UserFragment : BaseFragmentViewBinding<UserFragmentBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.DATA)
    var afkt: AfkT? = null

    override fun baseLayoutId(): Int = R.layout.user_fragment

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        afkt?.let { uiController.setAllBackground(it.color) }

        UserRouter.userProvider()?.apply {
            // 如果已经登录了
            if (isLogin()) {
                getUserInfo()?.let { user ->
                    binding.vidUfNicknameTv.text = user.nickName
                    // 加载用户图片
                    DevImageEngine.getEngine().display(
                        binding.vidUfAvatarIgview,
                        user.avatar,
                        ProjectUtils.getRoundConfig10()
                    )
                    // 加载背景
                    DevImageEngine.getEngine().loadDrawable(
                        activity,
                        DevSource.create(user.background),
                        null,
                        object : DrawableListener() {
                            @SuppressLint("NewApi")
                            override fun onResponse(
                                source: DevSource?,
                                value: Drawable?
                            ) {
                                binding.vidUfContentTv.background = value
                            }

                            override fun onStart(source: DevSource?) {
                            }

                            override fun onFailure(
                                source: DevSource?,
                                throwable: Throwable?
                            ) {
                                DevLogEngine.getEngine().eTag(TAG, throwable)
                            }
                        }
                    )
                }
            } else {
                binding.vidUfNicknameTv.text = "用户未登录"
            }
        }
    }
}
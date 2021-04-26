package afkt.module.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.facade.annotation.Route
import dev.core.lib.base.app.BaseActivityViewBinding
import dev.core.lib.bean.AfkT
import dev.core.lib.config.main.SplashRouter
import dev.core.router.user.UserRouter
import dev.engine.log.DevLogEngine
import dev.utils.DevFinal
import dev.utils.app.toast.ToastTintUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Route(path = SplashRouter.PATH_LAUNCHER, group = SplashRouter.GROUP)
class AppLauncherActivity : BaseActivityViewBinding<ViewBinding>() {

    override fun baseLayoutId(): Int = 0

    override fun isViewBinding(): Boolean = false

    override fun isContentAssistSafe(): Boolean = true

    override fun isAddTitleBar(): Boolean = false

    override fun isAddStatusBar(): Boolean = false

    override fun isStatusBarFrame(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var value = if (BuildConfig.isModular) "【模块化运行】" else "【整合运行】"

        ToastTintUtils.normal("延迟进入【首页容器页】")

        // 调用其他模块数据
        var isLogin: Boolean = UserRouter.userProvider()?.isLogin() == true
        DevLogEngine.getEngine().d("是否登录了 ${isLogin}")
        DevLogEngine.getEngine().d("是否存在 UserProvider ${UserRouter.userProvider()}")

        GlobalScope.launch {
            delay(2000L)
            // 直接通过 postcard.navigation() 跳转会显示 AppTheme.Launcher style windowBackground
            var postcard = SplashRouter.buildAppMain()
                .withObject(DevFinal.DATA, AfkT(value))
            try {
                LogisticsCenter.completion(postcard)
                var intent = Intent(mActivity, postcard.destination)
                intent.putExtras(postcard.extras)
                startActivity(intent)
            } catch (e: Exception) {
            }
            finish()
        }
    }

    override fun baseLayoutView(): View? {
        var igview = ImageView(this)
        igview.setBackgroundResource(R.drawable.launcher_gradient_bg)
        igview.scaleType = ImageView.ScaleType.FIT_XY
        return igview
    }

    override fun onBackPressed() {
    }
}
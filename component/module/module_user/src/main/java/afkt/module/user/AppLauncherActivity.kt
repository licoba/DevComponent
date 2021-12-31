package afkt.module.user

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import dev.core.lib.base.app.BaseActivityViewBinding
import dev.core.lib.bean.AfkT
import dev.core.router.user.UserRouter
import dev.utils.DevFinal
import dev.utils.app.toast.ToastTintUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppLauncherActivity : BaseActivityViewBinding<ViewBinding>() {

    override fun baseLayoutId(): Int = 0

    override fun isViewBinding(): Boolean = false

    override fun isContentAssistSafe(): Boolean = true

    override fun isAddTitleBar(): Boolean = false

    override fun isAddStatusBar(): Boolean = false

    override fun isStatusBarFrame(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 非组件化编译运行直接关闭页面
        if (!BuildConfig.isModular) {
            finish()
            return
        }

        ToastTintUtils.normal("延迟进入【首页容器页】")

        GlobalScope.launch {
            delay(2000L)
            UserRouter.build(UserRouter.PATH_MAIN)
                .withObject(DevFinal.STR.DATA, AfkT("【模块化运行】"))
                .navigation()
            finish()
        }
    }

    override fun baseLayoutView(): View {
        return ImageView(this).apply {
            setBackgroundResource(R.drawable.launcher_gradient_bg)
            scaleType = ImageView.ScaleType.FIT_XY
        }
    }

    override fun onBackPressed() {
    }
}
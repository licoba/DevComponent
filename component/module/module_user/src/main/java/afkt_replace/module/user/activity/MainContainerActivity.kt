package afkt_replace.module.user.activity

import afkt_replace.core.lib.base.app.BaseActivityViewBinding
import afkt_replace.core.lib.bean.AfkT
import afkt_replace.core.router.user.UserRouter
import afkt_replace.module.user.BuildConfig
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal
import dev.utils.app.ClickUtils
import dev.utils.app.toast.ToastTintUtils

@Route(path = UserRouter.PATH_MAIN, group = UserRouter.GROUP)
class MainContainerActivity : BaseActivityViewBinding<ViewBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.DATA)
    var afkt: AfkT? = null

    override fun baseLayoutId(): Int = 0

    override fun isViewBinding(): Boolean = false

    override fun isContentAssistSafe(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar.setTitle("${BuildConfig.MODULE_NAME} 首页容器页")
            .setTitleColor(Color.WHITE)
            .goneBackView()

        afkt?.let { uiController.setAllBackground(it.color) }

        (UserRouter.build(UserRouter.PATH_USER_FRAGMENT)
            .with(intent.extras).navigation() as? Fragment)?.let { fragment ->
            supportFragmentManager.beginTransaction().apply {
                add(contentAssist.contentLinear!!.id, fragment)
                commit()
            }
        }
    }

    override fun onBackPressed() {
        if (!ClickUtils.isFastDoubleClick(DevFinal.STR.TAG, 1500L)) {
            ToastTintUtils.info("再按一次，退出应用")
            return
        }
        super.onBackPressed()
    }
}
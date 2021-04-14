package afkt.module.aaa.activity

import afkt.module.aaa.BuildConfig
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import dev.core.lib.base.app.BaseActivityViewBinding
import dev.core.lib.bean.AfkT
import dev.core.lib.config.aaa.AAARoute
import dev.utils.DevFinal


@Route(path = AAARoute.LAUNCHER_PATH, group = AAARoute.GROUP)
class LauncherActivity : BaseActivityViewBinding<ViewBinding>() {

    @JvmField
    @Autowired(name = DevFinal.DATA)
    var afkt: AfkT? = null

    override fun baseLayoutId(): Int = 0

    override fun isViewBinding(): Boolean = false

    override fun isContentAssistSafe(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar.setBackListener { finish() }

        if (BuildConfig.isModular) {
            afkt = AfkT("模块化运行")
        }

        afkt?.let { uiController.setAllBackground(it.color) }

        /**
         * [BuildConfig.isModular]
         */
        (ARouter.getInstance().build(AAARoute.MAIN_FRAGMENT_PATH, AAARoute.GROUP)
            .withObject(DevFinal.DATA, afkt)
            .navigation() as? Fragment)?.let { fragment ->
            supportFragmentManager.beginTransaction().apply {
                add(contentAssist.contentLinear!!.id, fragment)
                commit()
            }
        }

//        // 可以直接这样传值 @Autowired 也会自动解析
//        (ARouter.getInstance().build(AAARoute.MAIN_FRAGMENT_PATH, AAARoute.GROUP)
//            .with(intent.extras)
//            .navigation() as? Fragment)?.let { fragment ->
//            supportFragmentManager.beginTransaction().apply {
//                add(contentAssist.contentLinear!!.id, fragment)
//                commit()
//            }
//        }
    }
}
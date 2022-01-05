package afkt.module.main.activity

import afkt.module.main.BuildConfig
import afkt.module.main.R
import afkt.module.main.adapter.MainAdapter
import afkt.module.main.databinding.MainActivityBinding
import android.graphics.Color
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.core.lib.base.app.BaseActivityViewBinding
import dev.core.lib.bean.AfkT
import dev.core.router.main.MainRouter
import dev.utils.DevFinal
import dev.utils.app.ClickUtils
import dev.utils.app.toast.ToastTintUtils

@Route(path = MainRouter.PATH_MAIN, group = MainRouter.GROUP)
class MainContainerActivity : BaseActivityViewBinding<MainActivityBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.DATA)
    var afkt: AfkT? = null

    override fun baseLayoutId(): Int = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar.setTitle("${BuildConfig.MODULE_NAME} 首页容器页")
            .setTitleColor(Color.WHITE)
            .goneBackView()

        afkt?.let { uiController.setAllBackground(it.color) }

        if (!BuildConfig.isModular) {
            binding.vidViewpager.adapter = MainAdapter(afkt, this)
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
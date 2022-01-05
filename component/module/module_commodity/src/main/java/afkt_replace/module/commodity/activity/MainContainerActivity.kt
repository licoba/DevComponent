package afkt_replace.module.commodity.activity

import afkt_replace.core.lib.base.app.BaseActivityViewBinding
import afkt_replace.core.lib.bean.ThemeIntent
import afkt_replace.core.router.commodity.CommodityRouter
import afkt_replace.module.commodity.BuildConfig
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal
import dev.utils.app.ClickUtils
import dev.utils.app.toast.ToastTintUtils

@Route(path = CommodityRouter.PATH_MAIN, group = CommodityRouter.GROUP)
class MainContainerActivity : BaseActivityViewBinding<ViewBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.DATA)
    var themeIntent: ThemeIntent? = null

    override fun baseLayoutId(): Int = 0

    override fun isViewBinding(): Boolean = false

    override fun isContentAssistSafe(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar.setTitle("${BuildConfig.MODULE_NAME} 首页容器页")
            .setTitleColor(Color.WHITE)
            .goneBackView()

        themeIntent?.let { uiController.setAllBackground(it.color) }

        (CommodityRouter.build(CommodityRouter.PATH_SHOP_CART_FRAGMENT)
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
package afkt_replace.module.wan_android.feature.article

import afkt_replace.core.lib.base.app.BaseFragmentViewBinding
import afkt_replace.core.lib.bean.ThemeIntent
import afkt_replace.core.lib.router.module.wan_android.WanAndroidRouter
import afkt_replace.module.wan_android.R
import afkt_replace.module.wan_android.databinding.WanAndroidFragmentArticleBinding
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal

@Route(path = WanAndroidRouter.PATH_ARTICLE_FRAGMENT, group = WanAndroidRouter.GROUP)
class ArticleFragment : BaseFragmentViewBinding<WanAndroidFragmentArticleBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.DATA)
    var themeIntent: ThemeIntent? = null

    override fun baseLayoutId(): Int = R.layout.wan_android_fragment_article

    override fun preLoad() {
        super.preLoad()
        themeIntent?.let { uiController.setAllBackground(it.color) }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }
}
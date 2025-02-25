package afkt_replace.module.template.feature

import afkt_replace.core.lib.base.app.BaseFragmentViewBinding
import afkt_replace.core.lib.bean.ThemeIntent
import afkt_replace.core.lib.router.module.template.TemplateRouter
import afkt_replace.module.template.R
import afkt_replace.module.template.databinding.TemplateFragmentAaaBinding
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal

@Route(path = TemplateRouter.PATH_AAA_FRAGMENT, group = TemplateRouter.GROUP)
class AAAFragment : BaseFragmentViewBinding<TemplateFragmentAaaBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.DATA)
    var themeIntent: ThemeIntent? = null

    override fun baseLayoutId(): Int = R.layout.template_fragment_aaa

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.vidContentTv.text = "$TAG \r\n ${themeIntent?.value}"

        themeIntent?.let { uiController.setAllBackground(it.color) }
    }
}
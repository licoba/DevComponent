package afkt.module.aaa.fragment

import afkt.module.aaa.R
import afkt.module.aaa.databinding.AaaFragmentBinding
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import afkt_replace.core.lib.base.app.BaseFragmentViewBinding
import afkt_replace.core.lib.bean.AfkT
import dev.core.router.aaa.AAARouter
import dev.utils.DevFinal

@Route(path = AAARouter.PATH_AAA_FRAGMENT, group = AAARouter.GROUP)
class AAAFragment : BaseFragmentViewBinding<AaaFragmentBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.DATA)
    var afkt: AfkT? = null

    override fun baseLayoutId(): Int = R.layout.aaa_fragment

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.vidContentTv.text = "$TAG \r\n ${afkt?.value}"

        afkt?.let { uiController.setAllBackground(it.color) }
    }
}
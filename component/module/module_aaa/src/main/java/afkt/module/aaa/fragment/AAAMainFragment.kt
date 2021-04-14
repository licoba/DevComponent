package afkt.module.aaa.fragment

import afkt.module.aaa.R
import afkt.module.aaa.databinding.AaaFragmentMainBinding
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.core.lib.base.app.BaseFragmentViewBinding
import dev.core.lib.bean.AfkT
import dev.core.lib.config.aaa.AAARoute
import dev.utils.DevFinal

@Route(path = AAARoute.MAIN_FRAGMENT_PATH, group = AAARoute.GROUP)
class AAAMainFragment : BaseFragmentViewBinding<AaaFragmentMainBinding>() {

    @JvmField
    @Autowired(name = DevFinal.DATA)
    var afkt: AfkT? = null

    override fun baseLayoutId(): Int = R.layout.aaa_fragment_main

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.vidAfmContextTv.text = "$TAG \r\n ${afkt?.value}"

        afkt?.let { uiController.setAllBackground(it.color) }
    }
}
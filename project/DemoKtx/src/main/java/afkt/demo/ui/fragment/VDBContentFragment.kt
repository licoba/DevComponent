package afkt.demo.ui.fragment

import afkt.demo.R
import afkt.demo.databinding.FragmentVdbContentBinding
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import dev.base.expand.content.DevBaseContentViewDataBindingFragment
import dev.utils.LogPrintUtils
import dev.utils.app.ResourceUtils
import dev.utils.app.TextViewUtils
import dev.utils.app.ViewUtils
import dev.utils.app.helper.QuickHelper
import dev.utils.common.ChineseUtils
import dev.utils.common.RandomUtils

class VDBContentFragment : DevBaseContentViewDataBindingFragment<FragmentVdbContentBinding>() {

    override fun baseLayoutId(): Int {
        return R.layout.fragment_vdb_content
    }

    override fun baseLayoutView(): View? {
        return null
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // 设置图片
        binding.image = ResourceUtils.getDrawable(R.mipmap.icon_launcher_round)
        // 设置随机内容
        binding.content = ChineseUtils.randomWord(RandomUtils.getRandom(40, 80))

        contentAssist.addTitleView(
            QuickHelper.get(TextView(context))
                .setText("Fragment new TextView ( addTitleView )")
                .setTextColor(ResourceUtils.getColor(R.color.black))
                .setWidthHeight(
                    ViewUtils.MATCH_PARENT,
                    ResourceUtils.getDimensionInt(R.dimen.un_dp_50)
                )
                .setTextGravity(Gravity.CENTER)
                .setBold()
                .setOnClicks { view ->
                    showToast(TextViewUtils.getText(view))
                }
                .getView()
        )

        LogPrintUtils.dTag(
            ParentFragment.LOG_TAG,
            "VDBContentFragment => parentFragment: %s",
            parentFragment
        )

        // 嵌套处理
        ParentFragment.commit(childFragmentManager, binding.vidFvdbFrame.id, 0, 4)
    }
}
package afkt.demo.ui.fragment

import afkt.demo.R
import afkt.demo.databinding.FragmentParentBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import dev.base.expand.viewbinding.DevBaseViewBindingFragment
import dev.utils.DevFinal
import dev.utils.LogPrintUtils

/**
 * detail: 测试 parentFragment
 * @author Ttt
 */
class ParentFragment : DevBaseViewBindingFragment<FragmentParentBinding>() {

    override fun baseContentId(): Int {
        return R.layout.fragment_parent
    }

    override fun baseContentView(): View? {
        return null
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            var position = it.getInt(DevFinal.POSITION)
            var max = it.getInt(DevFinal.MAX)

            var positionStr = (position + 1).toString()

            // 设置索引文案
            binding.vidFpPositionTv.text = positionStr
            // 判断是否达到最大值
            if (position >= max) return

            // 设置 Fragment
            commit(childFragmentManager, binding.vidFpFrame.id, position + 1, max)
        }

        LogPrintUtils.dTag(LOG_TAG, "ParentFragment => parentFragment: %s", parentFragment)
    }

    companion object {
        fun get(
            position: Int,
            max: Int
        ): DevBaseViewBindingFragment<FragmentParentBinding> {
            val fragment = ParentFragment()
            val bundle = Bundle()
            bundle.putInt(DevFinal.POSITION, position)
            bundle.putInt(DevFinal.MAX, max)
            fragment.arguments = bundle
            return fragment
        }

        fun commit(
            manager: FragmentManager,
            id: Int,
            position: Int,
            max: Int
        ) {
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.add(id, get(position, max))
            transaction.commit()
        }

        const val LOG_TAG = "ParentFragment_TAG"
    }
}
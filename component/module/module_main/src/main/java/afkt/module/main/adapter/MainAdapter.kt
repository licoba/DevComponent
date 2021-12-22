package afkt.module.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.core.lib.bean.AfkT
import dev.core.router.aaa.AAARouter
import dev.core.router.commodity.CommodityRouter
import dev.core.router.user.UserRouter
import dev.utils.DevFinal

class MainAdapter(
    var afkt: AfkT?,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AAARouter.build(
                    AAARouter.PATH_AAA_FRAGMENT
                ).withObject(DevFinal.STR.DATA, afkt).navigation() as Fragment
            }
            1 -> {
                CommodityRouter.build(
                    CommodityRouter.PATH_SHOP_CART_FRAGMENT
                ).withObject(DevFinal.STR.DATA, afkt).navigation() as Fragment
            }
            else -> {
                UserRouter.build(
                    UserRouter.PATH_USER_FRAGMENT
                ).withObject(DevFinal.STR.DATA, afkt).navigation() as Fragment
            }
        }
    }
}
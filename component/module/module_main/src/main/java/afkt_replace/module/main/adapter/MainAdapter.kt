package afkt_replace.module.main.adapter

import afkt_replace.core.lib.bean.ThemeIntent
import afkt_replace.core.router.commodity.CommodityRouter
import afkt_replace.core.router.template.TemplateRouter
import afkt_replace.core.router.user.UserRouter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.utils.DevFinal

class MainAdapter(
    var themeIntent: ThemeIntent?,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TemplateRouter.build(
                    TemplateRouter.PATH_AAA_FRAGMENT
                ).withObject(DevFinal.STR.DATA, themeIntent).navigation() as Fragment
            }
            1 -> {
                CommodityRouter.build(
                    CommodityRouter.PATH_SHOP_CART_FRAGMENT
                ).withObject(DevFinal.STR.DATA, themeIntent).navigation() as Fragment
            }
            else -> {
                UserRouter.build(
                    UserRouter.PATH_USER_FRAGMENT
                ).withObject(DevFinal.STR.DATA, themeIntent).navigation() as Fragment
            }
        }
    }
}
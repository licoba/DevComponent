package afkt.module.commodity.adapter

import afkt_replace.libs.commodity.utils.appendLabel
import afkt.module.commodity.R
import afkt.module.commodity.databinding.CommodityAdapterShopCartBinding
import android.view.View
import android.view.ViewGroup
import dev.adapter.DevDataAdapterExt2
import dev.base.DevPage
import dev.base.adapter.DevBaseViewBindingVH
import dev.base.adapter.newBindingViewHolder
import afkt_replace.core.lib.bean.commodity.CommodityBean
import afkt_replace.core.lib.utils.ProjectUtils
import dev.engine.DevEngine
import dev.utils.app.ViewUtils
import dev.utils.app.helper.view.ViewHelper
import dev.utils.common.BigDecimalUtils
import java.math.BigDecimal

/**
 * detail: 购物车 Adapter
 * @author Ttt
 */
class ShopCartAdapter :
    DevDataAdapterExt2<CommodityBean, DevBaseViewBindingVH<CommodityAdapterShopCartBinding>>() {

    init {
        page = DevPage(1, 10)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DevBaseViewBindingVH<CommodityAdapterShopCartBinding> {
        return newBindingViewHolder(parent, R.layout.commodity_adapter_shop_cart)
    }

    override fun onBindViewHolder(
        holder: DevBaseViewBindingVH<CommodityAdapterShopCartBinding>,
        position: Int
    ) {
        val item: CommodityBean = getDataItem(position)

        // 商品信息
        ViewHelper.get()
            .setText(
                appendLabel(item.commodityName, item.type).create(),
                holder.binding.vidNameTv
            )
            .setText(
                "￥" + BigDecimalUtils.round(
                    item.price, 2, BigDecimal.ROUND_HALF_UP
                ),
                holder.binding.vidPriceTv
            )
        // 商品图片
        DevEngine.getImage().display(
            holder.binding.vidPicIgview,
            item.picture,
            ProjectUtils.getRoundConfig3()
        )

        // ==========
        // = 多选处理 =
        // ==========

        val key = getMultiSelectKey(item, position)
        val selectIGView = holder.binding.vidIgview
        // 是否显示编辑按钮、以及是否选中
        ViewHelper.get().setVisibilitys(isEditState, selectIGView)
            .setSelected(mMultiSelectMap.isSelectKey(key), selectIGView)
            .setOnClick(View.OnClickListener {
                if (!isEditState) return@OnClickListener
                // 反选处理
                mMultiSelectMap.toggle(key, item)
                // 设置是否选中
                ViewUtils.setSelected(mMultiSelectMap.isSelectKey(key), selectIGView)
                // 触发回调
                if (selectListener != null) {
                    selectListener!!.onClickSelect(position, mMultiSelectMap.isSelectKey(key))
                }
            }, holder.itemView)
    }

    // =======
    // = 多选 =
    // =======

    override fun getMultiSelectKey(
        item: CommodityBean,
        position: Int
    ): String {
        return position.toString()
    }

    // =============
    // = 操作监听事件 =
    // =============

    // 选择事件通知事件
    var selectListener: OnSelectListener? = null

    /**
     * detail: 选择事件通知事件
     * @author Ttt
     */
    interface OnSelectListener {

        /**
         * 点击选中切换
         * @param position 对应的索引
         * @param now      新的状态
         */
        fun onClickSelect(
            position: Int,
            now: Boolean
        )
    }
}
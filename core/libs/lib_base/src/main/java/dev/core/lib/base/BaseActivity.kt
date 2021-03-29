package dev.core.lib.base

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dev.base.expand.content.DevBaseContentMVVMActivity
import dev.core.lib.ui.widget.BaseTitleBar
import dev.utils.app.BarUtils
import dev.utils.app.ScreenUtils

/**
 * detail: Base MVVM Activity
 * @author Ttt
 */
open abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModel> : DevBaseContentMVVMActivity<VDB, VM>() {

    @JvmField // Context
    protected var mContext: Context? = null

    @JvmField // Activity
    protected var mActivity: Activity? = null

    // =

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isAddStatusBar()) {
            // 设置无标题
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            // 设置透明 StatusBar
            BarUtils.transparentStatusBar(this)
            // 设置非高亮模式 ( 状态栏图标、文字黑色 )
            BarUtils.setStatusBarLightMode(this, true)
        }
        super.onCreate(savedInstanceState)
        mContext = this
        mActivity = this
        // 初始化 ViewModel
        initViewModel()
        // 添加基础骨架 View
        addBaseView()
        // 初始化方法
        initOrder()
    }

    // ===========
    // = 接口方法 =
    // ===========

    override fun baseLayoutView(): View? = null

    /**
     * 是否添加 TitleBar
     */
    open fun isAddTitleBar(): Boolean = true

    /**
     * 是否添加 StatusBar
     * @return Boolean
     */
    open fun isAddStatusBar(): Boolean = true

    // ===========
    // = 内部操作 =
    // ===========

    // StatusBar View
    lateinit var statusBar: View

    // TitleBar View
    lateinit var titleBar: BaseTitleBar

    /**
     * 添加基础骨架 View
     */
    private fun addBaseView() {
        if (isAddTitleBar()) {
            titleBar = BaseTitleBar(this)
            contentAssist.addTitleView(titleBar)
        }
        if (isAddStatusBar()) {
            statusBar = View(this)
            statusBar.setBackgroundColor(Color.WHITE)
            val statusBarHeight: Int = ScreenUtils.getStatusBarHeight()
            statusBar.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight
            )
            contentAssist.addStatusBarView(statusBar)
        }
    }
}
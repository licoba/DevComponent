package dev.core.lib.base.controller

import dev.base.utils.assist.DevBaseContentAssist
import dev.utils.app.ViewUtils
import dev.utils.app.helper.QuickHelper

/**
 * detail: 基础 UI 控制封装
 * @author Ttt
 */
class BaseUIController(var contentAssist: DevBaseContentAssist) {

    /**
     * 设置全部背景色
     * @param bgColor Int
     */
    fun setAllBackground(bgColor: Int) {
        QuickHelper.get(contentAssist.titleLinear).setBackgroundColor(bgColor)
            .quickHelper(contentAssist.statusBarLinear).setBackgroundColor(bgColor)

        ViewUtils.getChilds(contentAssist.statusBarLinear).forEach {
            ViewUtils.setBackgroundColor(it, 0)
        }
    }
}
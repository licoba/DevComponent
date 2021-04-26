package dev.core.lib.bean

import dev.utils.common.ColorUtils

/**
 * detail: 自定义对象 ARouter 传递
 * @author Ttt
 */
open class AfkT() {

    var value: String? = null

    var color: Int = 0

    constructor(
        value: String?,
    ) : this(
        value,
        ColorUtils.setAlphaDark(ColorUtils.getRandomColor(), 100)
    )

    constructor(
        value: String?,
        color: Int
    ) : this() {
        this.value = value
        this.color = color
    }
}
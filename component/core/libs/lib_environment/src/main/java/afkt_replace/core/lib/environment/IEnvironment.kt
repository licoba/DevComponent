package afkt_replace.core.lib.environment

import android.content.Context

/**
 * detail: APP 环境配置
 * @author Ttt
 * 该类用于给 Core Module AppEnvironment 类实现
 * 内部实现代码, 外部直接通过 AppEnvironment 进行调用无需关注内部实现逻辑
 * 减少该模块暴露
 */
interface IEnvironment {

    /**
     * 环境校验
     * @param context Context
     * @param index Int
     * 需在 Application 内尽可能的早调用
     */
    fun checker(
        context: Context,
        index: Int
    ) {
        EnvironmentTypeChecker.checker(context, index)
    }
}
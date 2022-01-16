package afkt_replace.core.lib.environment

import android.content.Context

/**
 * detail: APP 环境配置
 * @author Ttt
 */
interface IEnvironment {

    /**
     * 环境校验
     * @param context Context
     * @param index Int
     */
    fun checker(
        context: Context,
        index: Int
    ) {
        EnvironmentTypeChecker.checker(context, index)
    }
}
package afkt_replace.core.lib.environment

/**
 * detail: APP 环境配置
 * @author Ttt
 * 该类用于给 Core Module AppEnvironment 类实现
 * 内部实现代码, 外部直接通过 AppEnvironment 进行调用无需关注内部实现逻辑
 * 减少该模块暴露
 */
interface IEnvironment {

    /**
     * 环境校验与重置
     * 需在 Application 内尽可能的早调用
     * 用于非 Release 版本下针对自动化构建工具支持环境切换处理
     */
    fun checker() {
        EnvironmentTypeChecker.checker()
    }
}
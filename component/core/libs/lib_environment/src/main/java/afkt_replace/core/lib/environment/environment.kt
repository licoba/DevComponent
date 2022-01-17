package afkt_replace.core.lib.environment

import afkt_replace.core_lib_environment.BuildConfig
import android.content.Context
import dev.environment.DevEnvironmentActivity
import dev.environment.annotation.Environment
import dev.environment.annotation.Module
import dev.utils.DevFinal
import dev.utils.app.share.SPUtils

/**
 * detail: Http Base Service 服务器请求地址
 * @author Ttt
 */
internal class HttpService private constructor() {

    @Module(alias = "登录、注册模块")
    private inner class Login {
        @Environment(value = "https://login-release.com", isRelease = true, alias = "生产环境")
        private val release: String? = null

        @Environment(value = "http://login-debug.com", alias = "测试环境")
        private val debug: String? = null

        @Environment(value = "http://login-pre_release.com", alias = "预发布环境")
        private val pre_release: String? = null

        @Environment(value = "http://login-development.com", alias = "开发环境")
        private val development: String? = null
    }

    @Module(alias = "用户模块")
    private inner class User {
        @Environment(value = "https://user-release.com", isRelease = true, alias = "生产环境")
        private val release: String? = null

        @Environment(value = "http://user-debug.com", alias = "测试环境")
        private val debug: String? = null

        @Environment(value = "http://user-pre_release.com", alias = "预发布环境")
        private val pre_release: String? = null

        @Environment(value = "http://user-development.com", alias = "开发环境")
        private val development: String? = null
    }

    @Module(alias = "商品模块")
    private inner class Commodity {
        @Environment(value = "https://commodity-release.com", isRelease = true, alias = "生产环境")
        private val release: String? = null

        @Environment(value = "http://commodity-debug.com", alias = "测试环境")
        private val debug: String? = null

        @Environment(value = "http://commodity-pre_release.com", alias = "预发布环境")
        private val pre_release: String? = null

        @Environment(value = "http://commodity-development.com", alias = "开发环境")
        private val development: String? = null
    }
}

/**
 * detail: Build Config Field Environment Type 构建校验
 * @author Ttt
 */
internal object EnvironmentTypeChecker {

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 内部环境设置
     * @param context Context
     * HttpService 每新增一个模块, 则需要在改方法新增处理
     */
    private fun innerEnvironmentSet(context: Context) {
        when (BuildConfig.environmentType) {
            1 -> { // 生产环境
            }
            2 -> { // 测试环境

            }
            3 -> { // 预发布环境

            }
            4 -> { // 开发环境

            }
        }
    }

    // =============
    // = 对外公开方法 =
    // =============

    /**
     * 环境校验与重置
     * @param context Context
     * 需在 Application 内尽可能的早调用
     * 用于非 Release 版本下针对自动化构建工具支持环境切换处理
     */
    fun checker(context: Context) {
        // 非 Release 版本才进行处理
        if (!BuildConfig.isRelease) {
            val sp = SPUtils.getPreference(
                context, BuildConfig.MODULE_NAME
            )
            // 上次构建时间
            val oldTime = sp?.getLong(DevFinal.STR.BUILD) ?: 0
            if (BuildConfig.BUILD_TIME > oldTime) {
                innerEnvironmentSet(context)
                sp?.put(DevFinal.STR.BUILD, BuildConfig.BUILD_TIME)
            }
        }
    }
}
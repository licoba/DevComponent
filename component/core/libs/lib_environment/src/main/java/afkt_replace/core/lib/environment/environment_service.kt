package afkt_replace.core.lib.environment

import dev.environment.annotation.Environment
import dev.environment.annotation.Module

// ===============
// = 服务器请求地址 =
// ===============

/**
 * detail: Http Base Service 服务器请求地址
 * @author Ttt
 */
internal class HttpService private constructor() {

    @Module(alias = "启动页 ( 广告页、首次启动引导页 ) 模块")
    private inner class Splash {
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
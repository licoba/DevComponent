
# About

网络相关 lib ( 网络请求、上传下载 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // 依赖 Engine ( 编译但不参与打包 ) => 例 JSON 相关使用
    compileOnly project(':core_lib_engine')

    // ====================
    // = libs - 第三方库依赖 =
    // ====================

    // okip https://github.com/square/okio
    api deps.lib.okio
    // OkHttp3 网络请求框架 https://github.com/square/okhttp
    api deps.lib.okhttp3
    api deps.lib.okhttp3_logging
    // Retrofit 网络请求库  https://github.com/square/retrofit
    api deps.lib.retrofit
    // Retrofit Gson Converter  https://github.com/square/retrofit/tree/master/retrofit-converters/gson
    api deps.lib.retrofit_gson
    // Retrofit RxJava3 Adapter  https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava3
    api deps.lib.retrofit_rxjava3

//    // android:authorities="com.ding.library" => INSTALL_FAILED_CONFLICTING_PROVIDER
//    // OkHttp 拦截器抓包 https://github.com/DingProg/NetworkCaptureSelf
//    debugApi deps.property.networkCaptureSelf
//    releaseApi deps.property.networkCaptureSelf_op
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="afkt_replace.core_lib_network">

    <!-- 网络权限 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <!-- 读写权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
</manifest>
```
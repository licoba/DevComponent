
# About

基础核心开发库依赖 ( libs 便捷依赖统一维护 )

# 依赖信息

```groovy
dependencies {

    // ================
    // = Dev 系列开发库 =
    // ================

    // https://github.com/afkT/DevUtils

    // DevApp - Android 工具类库
    api deps.dev.dev_app_x

    // DevAssist - 封装逻辑代码, 实现多个快捷功能辅助类、以及 Engine 兼容框架等
    api deps.dev.dev_assist

    // DevBase - Base ( Activity、Fragment )、MVP、ViewBinding、ContentLayout 基类库
    api deps.dev.dev_base

    // DevBaseMVVM - MVVM ( ViewDataBinding + ViewModel ) 基类库
    api deps.dev.dev_base_mvvm

    // DevEngine - 第三方框架解耦、一键替换第三方库、同类库多 Engine 组件化混合使用
    api deps.dev.dev_engine

    // DevWidget - 自定义 View UI 库
    api deps.dev.dev_widget_x

    // DevEnvironment - Android 环境配置切换库
    api deps.dev.dev_environment
//    if (isRelease) {
//        kapt deps.dev.dev_environment_compiler_release
//    } else {
//        kapt deps.dev.dev_environment_compiler
//    }

    // DevHttpCapture - OkHttp 抓包工具库
    api deps.dev.dev_http_capture
    // DevHttpCaptureCompiler - OkHttp 抓包工具库 ( 可视化功能 )
    if (isRelease) {
        api deps.dev.dev_http_capture_compiler_release
    } else {
        api deps.dev.dev_http_capture_compiler
    }

    // DevHttpManager - OkHttp 管理库 ( Retrofit 多 BaseUrl 等 )
    api deps.dev.dev_http_manager

    // ===========
    // = Android =
    // ===========

    api deps.kotlin.stdlib
    api deps.kotlin.core
    api deps.kotlin.coroutines
    api deps.kotlin.lifecycle_runtime
    api deps.kotlin.lifecycle_viewmodel
    api deps.kotlin.lifecycle_livedata
    api deps.kotlin.lifecycle_viewmodel_savedstate
    api deps.kotlin.lifecycle_common_java8
    api deps.kotlin.work_runtime_ktx
    api deps.kotlin.work_runtime
    api deps.kotlin.fragment_ktx
    api deps.kotlin.activity_ktx
    api deps.androidx.design
    api deps.androidx.appcompat
    api deps.androidx.appcompat_resources
    api deps.androidx.cardview
    api deps.androidx.recyclerview
    api deps.androidx.multidex
    api deps.androidx.constraint_layout
    api deps.androidx.viewpager2
    api deps.androidx.fragment
    api deps.androidx.flexbox
    api deps.androidx.hilt_android
    kapt deps.androidx.hilt_android_compiler
    api deps.androidx.startup

    // ====================
    // = libs - 第三方库依赖 =
    // ====================

    // RxJava3 https://github.com/ReactiveX/RxJava/tree/3.x
    api deps.lib.rxjava3
    // RxAndroid3 https://github.com/ReactiveX/RxAndroid/tree/3.x
    api deps.lib.rxandroid3
    // RxLifecycle https://github.com/trello/RxLifecycle
    api deps.lib.rxlifecycle
    api deps.lib.rxlifecycle_kotlin
    api deps.lib.rxlifecycle_android_lifecycle_kotlin
    // AutoDispose 自动绑定解绑 https://github.com/uber/AutoDispose
    api deps.lib.auto_dispose
    api deps.lib.auto_dispose_android
    api deps.lib.auto_dispose_lifecycle
    // LiveEventBus 消息总线 https://github.com/JeremyLiao/LiveEventBus
    api deps.lib.live_eventbus
    // 基于 mmap 的高性能通用 key-value 组件 https://github.com/Tencent/MMKV/blob/master/readme_cn.md
    api deps.lib.mmkv
    // 今日头条屏幕适配方案终极版 https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md
    api deps.lib.autosize
    // 动画库 https://github.com/airbnb/lottie-android
    api deps.lib.lottie
    // 二维码 ZXing https://github.com/zxing/zxing
    api deps.lib.zxing_code // http://mvnrepository.com/artifact/com.google.zxing/core
    api deps.lib.zxing_android_code // android-core
    // 功能面板切换辅助 https://github.com/YummyLau/PanelSwitchHelper/blob/master/README-zh.md
    api deps.lib.panelSwitchHelper

    // ARouter 路由 https://github.com/alibaba/ARouter
    api deps.lib.arouter_api // https://github.com/alibaba/ARouter/blob/master/README_CN.md
    kapt deps.lib.arouter_compiler

    // ==========
    // = 打包相关 =
    // ==========

    // 瓦力多渠道打包 https://github.com/Meituan-Dianping/walle
    api deps.build_apk.walle
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core.base.lib" />
```
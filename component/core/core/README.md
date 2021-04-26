
# About

核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库
    api project(':core_base_lib')

    // ====================
    // = core - 核心开发库 =
    // ====================

    // 基础 ( 基类等 ) lib
    api project(':core_lib_base')
    // 通用实体类 lib
    api project(':core_lib_bean')
    // 通用配置、常量 lib
    api project(':core_lib_config')
    // 通用 Engine ( 图片加载、日志、JSON、权限、资源选择 等 ) lib
    api project(':core_lib_engine')
    // 网络框架 lib
    api project(':core_lib_network')
    // 通用 UI 样式、资源、交互、控件 lib
    api project(':core_lib_ui')
    // 通用工具库
    api project(':core_lib_utils')

    // ===============
    // = 第三方库依赖 =
    // ===============

    // 瓦力多渠道打包 https://github.com/Meituan-Dianping/walle
    api deps.build_apk.walle

    // Bugly https://bugly.qq.com/docs/
    api deps.property.bugly
    api deps.property.bugly_ndk

    // ARouter 路由 https://github.com/alibaba/ARouter
    api deps.lib.arouter_api // https://github.com/alibaba/ARouter/blob/master/README_CN.md
    kapt deps.lib.arouter_compiler

    // ======================
    // = 性能检测、排查相关库 =
    // ======================

    // xCrash Android 应用崩溃捕获工具 https://github.com/iqiyi/xCrash/blob/master/README.zh-CN.md
    api deps.property.xcrash

    // 是否编译调试工具
    if (showDebugTools) {
        // 内存检测工具 https://github.com/square/leakcanary
        api deps.property.leakcanary
        // 应用数据库展示 https://github.com/guolindev/Glance
        api deps.property.glance
        // 饿了么 UETool https://github.com/eleme/UETool/blob/master/README_zh.md
        api deps.property.uetool
        // BlockCanary 性能监控组件 https://github.com/markzhai/AndroidPerformanceMonitor/blob/master/README_CN.md
        api deps.property.blockcanary_android
    } else {
        // 饿了么 UETool https://github.com/eleme/UETool/blob/master/README_zh.md
        api deps.property.uetool_no_op
        // BlockCanary 性能监控组件 https://github.com/markzhai/AndroidPerformanceMonitor/blob/master/README_CN.md
        api deps.property.blockcanary_no_op
    }

//    // 饿了么 UETool https://github.com/eleme/UETool/blob/master/README_zh.md
//    debugApi deps.property.uetool
//    releaseApi deps.property.uetool_no_op
//    // BlockCanary 性能监控组件 https://github.com/markzhai/AndroidPerformanceMonitor/blob/master/README_CN.md
//    debugApi deps.property.blockcanary_android
//    releaseApi deps.property.blockcanary_no_op
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="dev.core">

    <!-- 读取手机状态 -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />

    <application
        android:largeHeap="true"
        android:networkSecurityConfig="@xml/network_security_config"
        android:requestLegacyExternalStorage="false"
        android:supportsRtl="true"
        android:usesCleartextTraffic="true">

        <!-- 屏幕适配 -->

        <meta-data
            android:name="design_width_in_dp"
            android:value="360" />

        <meta-data
            android:name="design_height_in_dp"
            android:value="640" />

        <!-- App startup init -->

        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="dev.core.CoreInitializer"
                android:value="@string/androidx_startup" />
        </provider>
    </application>
</manifest>
```

# main/java 目录结构

```
- java                       
   - dev                     
      - core                 
         - app               
         - assist            
         - function          
         - property          
         - receiver          
         - router            
            - aaa            
            - commodity      
            - main           
            - splash         
            - user           
         - utils             
```


# main/res 目录结构

```
- res                        
   - xml                     
```


# About

基类相关 ( Activity、Application 等 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ===================
    // = core - 核心开发库 =
    // ===================

    // 通用配置、常量 lib
    compileOnly project(':core_lib_config')
    // 通用环境配置切换库
    compileOnly project(':core_lib_environment')
    // 通用 UI 样式、资源、交互、控件 lib
    compileOnly project(':core_lib_ui')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core_lib_base">

</manifest>
```

# main/java 目录结构

```
- java                           
   - afkt_replace                
      - core                     
         - lib                   
            - base               
               - app             
               - controller      
               - core            
```

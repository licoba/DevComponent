
# About

通用实体类 ( module 实体类下沉 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // 依赖 Engine ( 编译但不参与打包 ) => 例 JSON 相关使用
    compileOnly project(':core_lib_engine')

    // 依赖 Config ( 编译但不参与打包 )
    compileOnly project(':core_lib_config')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="dev.core_lib_bean">

</manifest>
```

# main/java 目录结构

```
- java                           
   - dev                         
      - core                     
         - lib                   
            - bean               
```


# About

WebView 相关

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // 依赖 Engine ( 编译但不参与打包 ) => 例 JSON 相关使用
    compileOnly project(':core_lib_engine')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="afkt_replace.core_lib_web">

</manifest>
```

# main/java 目录结构

```
- java                             
   - afkt_replace                  
      - core                       
         - lib                     
            - web                  
               - assist            
```

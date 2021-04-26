
# About

通用配置、常量信息

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest package="dev.core_lib_config">

</manifest>
```

# main/java 目录结构

```
- java                           
   - dev                         
      - core                     
         - lib                   
            - config             
```

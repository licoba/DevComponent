
# About

通用 Engine ( 图片加载、日志、JSON、权限、资源选择、缓存 ) lib

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core_base_lib')

    // ============
    // = libs 依赖 =
    // ============

    // Glide 加载框架 https://github.com/bumptech/glide
    api deps.lib.glide
    kapt deps.lib.glide_compiler
    // Glide 图形库 https://github.com/wasabeef/glide-transformations
    api deps.lib.glide_transformations
    // Gson https://github.com/google/gson
    api deps.lib.gson
    // fastjson https://github.com/alibaba/fastjson
    api deps.lib.fastjson
    // Android 平台下的图片选择器 https://github.com/LuckSiege/PictureSelector
    api deps.lib.pictureSelector
    // Luban 鲁班图片压缩 https://github.com/Curzibn/Luban
    api deps.lib.luban
}
```
# About

该目录下的 Module 在 `isModular=true` 的情况下, 都属于独立的应用可单独运行, 为 `false` 则都属于功能模块, 被主体应用 ( 壳 ) 所依赖使用

## 目录结构

```
- module                   | 根目录
   - module_commodity      | 商品相关 Module
   - module_main           | 首页 ( 底部存在 Button ) Module
   - module_qrcode         | 二维码扫描、生成相关 Module
   - module_splash         | 启动页 Module
   - module_temp_a         | **这是一个 占位演示 ~~A 模块~~**
   - module_temp_b         | **这是一个 占位演示 ~~B 模块~~**
   - module_user           | 用户 Module
```
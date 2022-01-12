# About

该目录存储组件化各组件相关 ( core、module、library ) 等代码，方便统一查找、维护

## 目录结构

```
- component           | 根目录
   - core             | 核心基础整合库
   - libs             | 通用 Library、第三方库 clone 差异化修改
   - module           | 具体功能模块 ( 可单独运行 )，被主体应用 ( 壳 ) 所依赖使用
```
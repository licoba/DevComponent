
## 目录结构

```
- core                   | 根目录
   - core                | 核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )
   - core_base_lib       | 基础核心开发库依赖 ( libs 便捷依赖统一维护 )
   - libs                | 具体功能拆分, 封装 lib
      - lib_base         | 基类相关 ( Activity、Application 等 )
      - lib_bean         | 通用实体类 ( module 实体类下沉 )
      - lib_config       | 通用配置、常量信息
      - lib_engine       | 通用 Engine ( 图片加载、日志、JSON、权限、资源选择 ) lib
      - lib_network      | 网络相关 lib ( 网络请求、上传下载 )
      - lib_storage      | 存储相关功能
      - lib_ui           | 统一 style、widget、ui 相关组件
      - lib_upgrade      | 应用升级功能模块
```
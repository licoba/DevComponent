package dev.standard.catalog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * detail: 目录生成配置
 * @author Ttt
 */
public final class Config {

    private Config() {
    }

    // 当前目录
    public static final String USER_DIR     = System.getProperty("user.dir");
    // 项目路径
    public static final String PROJECT_PATH = new File(System.getProperty("user.dir")).getAbsolutePath();

    // ===============
    // = Interesting =
    // ===============

    // Interesting 文件名
    public static final String              INTERESTING_DIR_NAME   = "interesting";
    // Interesting 项目本地路径
    public static final String              INTERESTING_LOCAL_PATH = USER_DIR + File.separator + INTERESTING_DIR_NAME;
    // Interesting 文件目录注释
    public static final Map<String, String> sInterestingCatalogMap = new HashMap<>();

    // ========
    // = Core =
    // ========

    // Core 文件名
    public static final String              CORE_DIR_NAME       = "core";
    // Core 文件本地路径
    public static final String              CORE_LOCAL_PATH     = USER_DIR + File.separator + CORE_DIR_NAME;
    // Core 文件目录注释
    public static final Map<String, String> sCoreCatalogMap     = new HashMap<>();
    // Core About 注释
    public static final Map<String, String> sCoreAboutMap       = new HashMap<>();
    // Core 忽略目录
    public static final List<String>        sCoreIgnoreCatalogs = new ArrayList<>();

    // ==========
    // = Module =
    // ==========

    // Module 文件名
    public static final String              MODULE_DIR_NAME       = "module";
    // Module 文件本地路径
    public static final String              MODULE_LOCAL_PATH     = USER_DIR + File.separator + MODULE_DIR_NAME;
    // Module 文件目录注释
    public static final Map<String, String> sModuleCatalogMap     = new HashMap<>();
    // Module About 注释
    public static final Map<String, String> sModuleAboutMap       = new HashMap<>();
    // Module 忽略目录
    public static final List<String>        sModuleIgnoreCatalogs = new ArrayList<>();

    // ========
    // = Libs =
    // ========

    // Libs 文件名
    public static final String              LIBS_DIR_NAME       = "libs";
    // Libs 文件本地路径
    public static final String              LIBS_LOCAL_PATH     = USER_DIR + File.separator + LIBS_DIR_NAME;
    // Libs 文件目录注释
    public static final Map<String, String> sLibsCatalogMap     = new HashMap<>();
    // Libs About 注释
    public static final Map<String, String> sLibsAboutMap       = new HashMap<>();
    // Libs 忽略目录
    public static final List<String>        sLibsIgnoreCatalogs = new ArrayList<>();

    static {

        // ========
        // = Core =
        // ========

        sCoreCatalogMap.put("core", "根目录");
        sCoreCatalogMap.put(".core", "核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )");
        sCoreCatalogMap.put(".core_base_lib", "基础核心开发库依赖 ( libs 便捷依赖统一维护 )");
        sCoreCatalogMap.put(".libs", "具体功能拆分, 封装 lib");
        sCoreCatalogMap.put(".libs.lib_base", "基类相关 ( Activity、Application 等 )");
        sCoreCatalogMap.put(".libs.lib_bean", "通用实体类 ( module 实体类下沉 )");
        sCoreCatalogMap.put(".libs.lib_config", "通用配置、常量信息");
        sCoreCatalogMap.put(".libs.lib_engine", "通用 Engine ( 图片加载、日志、JSON、权限、资源选择、缓存 ) lib");
        sCoreCatalogMap.put(".libs.lib_network", "网络相关 lib ( 网络请求、上传下载 )");
        sCoreCatalogMap.put(".libs.lib_ui", "统一 style、widget、ui 相关组件");

        sCoreIgnoreCatalogs.add("core");
        sCoreIgnoreCatalogs.add("core_base_lib");

        sCoreAboutMap.putAll(sCoreCatalogMap);

        // ==========
        // = Module =
        // ==========

        sModuleCatalogMap.put("module", "根目录");
        sModuleCatalogMap.put(".module_commodity", "商品相关 Module");
        sModuleCatalogMap.put(".module_main", "首页 Module");
        sModuleCatalogMap.put(".module_qrcode", "二维码扫描、生成相关 Module");
        sModuleCatalogMap.put(".module_splash", "启动页 ( 广告页、首次启动引导页 ) Module");
        sModuleCatalogMap.put(".module_temp_a", "这是一个 占位演示 A Module");
        sModuleCatalogMap.put(".module_temp_b", "这是一个 占位演示 B Module");
        sModuleCatalogMap.put(".module_user", "用户 Module");

        sModuleAboutMap.putAll(sModuleCatalogMap);
        sModuleAboutMap.put(".module_commodity", "商品相关 ( 如商品详情、购物车、商品列表复用等 ) Module");
        sModuleAboutMap.put(".module_main", "首页 ( 底部 Button 导航 ) Module");
        sModuleAboutMap.put(".module_user", "用户信息相关 Module");

        // ========
        // = Libs =
        // ========

        sLibsCatalogMap.put("libs", "根目录");
        sLibsCatalogMap.put(".lib_circle_igview", "clone CircleImageView 修改源码使用 ( 例 )");
        sLibsCatalogMap.put(".lib_commodity", "商品通用快捷工具库 ( 方便复用 - 例 )");

        sLibsAboutMap.putAll(sLibsCatalogMap);
        sLibsAboutMap.put(".lib_circle_igview", "clone CircleImageView 修改源码使用 ( 用于演示, 推荐使用 Material ShapeableImageView )");

        // ===============
        // = Interesting =
        // ===============

        sInterestingCatalogMap.put("interesting", "根目录");
        sInterestingCatalogMap.put(".DevStandard", "项目规范统一检测、生成替换等");
    }
}
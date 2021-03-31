package dev.standard.catalog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.utils.DevFinal;
import dev.utils.common.StringUtils;

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
        sCoreCatalogMap.put(".libs.lib_upgrade", "应用升级功能模块");

        sCoreIgnoreCatalogs.add("core");
        sCoreIgnoreCatalogs.add("core_base_lib");

        sCoreAboutMap.putAll(sCoreCatalogMap);

        // ==========
        // = Module =
        // ==========

        sModuleCatalogMap.put("module", "根目录");
        sModuleCatalogMap.put(".module_commodity", "商品相关 Module");
        sModuleCatalogMap.put(".module_main", "首页 ( 底部存在 Button ) Module");
        sModuleCatalogMap.put(".module_qrcode", "二维码扫描、生成相关 Module");
        sModuleCatalogMap.put(".module_splash", "启动页 Module");
        sModuleCatalogMap.put(".module_temp_a", "**这是一个 占位演示 ~~A 模块~~**");
        sModuleCatalogMap.put(".module_temp_b", "**这是一个 占位演示 ~~B 模块~~**");
        sModuleCatalogMap.put(".module_user", "用户 Module");
    }

    /**
     * 插入文档头部内容
     * @param path    文件路径 ( 模块 )
     * @param builder 待插入 {@link StringBuilder}
     */
    public static void insertHeadREADME(
            final String path,
            final StringBuilder builder
    ) {
        if (StringUtils.equals(path, CORE_LOCAL_PATH)) {
            builder.append("# About");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("该目录属于核心基础库代码, 整个组件化项目基于该基础上进行开发");
            builder.append(DevFinal.NEW_LINE_STR);
        } else if (StringUtils.equals(path, MODULE_LOCAL_PATH)) {
            builder.append("# About");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("该目录下的 Module 在 `isModular=true` 的情况下, 都属于独立的应用可单独运行, 为 `false` 则都属于功能模块, 被主体应用 ( 壳 ) 所依赖使用");
            builder.append(DevFinal.NEW_LINE_STR);
        }
    }

    /**
     * 插入文档尾部内容
     * @param path    文件路径 ( 模块 )
     * @param builder 待插入 {@link StringBuilder}
     */
    public static void insertTailREADME(
            final String path,
            final StringBuilder builder
    ) {
        if (StringUtils.equals(path, CORE_LOCAL_PATH)) {
            builder.append(DevFinal.NEW_LINE_STR);
            builder.append("# core/core");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("> 该 Module 依赖 core 核心开发库、核心第三方库等");
            builder.append(DevFinal.NEW_LINE_STR).append(">").append(DevFinal.NEW_LINE_STR);
            builder.append("> 统一维护核心库依赖, 对外只需要依赖该 Module 便可使用整个核心模块 ( core 文件以及内部所有 libs )");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("## core/core_base_lib");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("> 该 Module 基于 Dev 系列开发库搭建, 且不存在任何代码属于核心 lib 依赖 ( 全部开发基于该 module )");
            builder.append(DevFinal.NEW_LINE_STR).append(">").append(DevFinal.NEW_LINE_STR);
            builder.append("> 用于统一维护基础核心开发库依赖, 如有必须依赖底层库在此添加");
        }
    }
}
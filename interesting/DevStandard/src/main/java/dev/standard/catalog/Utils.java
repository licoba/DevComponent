package dev.standard.catalog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.utils.DevFinal;
import dev.utils.common.FileUtils;
import dev.utils.common.StringUtils;

/**
 * detail: 内部工具类
 * @author Ttt
 */
public final class Utils {

    private Utils() {
    }

    /**
     * 生成目录文件
     * @param path              文件路径
     * @param dirName           文件名
     * @param mapCatalog        对应目录注释
     * @param mapAbout          About 注释
     * @param listIgnoreCatalog 忽略目录
     * @param layer             目录层级
     */
    public static void generateCatalog(
            final String path,
            final String dirName,
            final Map<String, String> mapCatalog,
            final Map<String, String> mapAbout,
            final List<String> listIgnoreCatalog,
            final int layer,
            final boolean generateDependenciesCatalog
    ) {
        String        catalog = CatalogGenerate.generate(path, dirName, mapCatalog, listIgnoreCatalog, layer);
        StringBuilder builder = new StringBuilder();
        // 插入文档头部内容
        Utils.insertHeadREADME(builder, path);
        // 文档内容
        builder.append(DevFinal.NEW_LINE_STR)
                .append("## 目录结构")
                .append(DevFinal.NEW_LINE_STR_X2)
                .append(catalog);
        // 插入文档尾部内容
        Utils.insertTailREADME(builder, path);
        String readme = StringUtils.clearEndsWith(builder.toString(), "\n");
        try {
            FileUtils.saveFile(new File(path, "README.md"), readme.getBytes(DevFinal.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (generateDependenciesCatalog) {
            generateDependenciesCatalog(path, "", mapAbout);
        }
    }

    /**
     * 生成依赖目录文件
     * @param path     文件路径
     * @param dirName  文件名
     * @param mapAbout About 注释
     */
    private static void generateDependenciesCatalog(
            final String path,
            final String dirName,
            final Map<String, String> mapAbout
    ) {
        File     root  = new File(path, dirName.replaceAll("\\.", "//"));
        String[] names = root.list();
        for (String name : names) {
            String catalog     = dirName + "." + name;
            String catelogPath = path + catalog.replaceAll("\\.", "//") + File.separator;
            if (mapAbout.containsKey(catalog)) {
                File gradleFile = new File(catelogPath + "build.gradle");
                // 文件存在则进行生成
                if (gradleFile.exists()) {
                    generateDependenciesREADME(catelogPath, mapAbout.get(catalog));
                } else {
                    String dir = ".";
                    if (StringUtils.isNotEmpty(dirName)) {
                        dir += dirName + ".";
                        dir = "." + StringUtils.clearStartsWith(dir, ".");
                    }
                    generateDependenciesCatalog(path, dir + name, mapAbout);
                }
            }
        }
    }

    /**
     * 生成依赖目录文件
     * @param path  文件路径
     * @param about module 功能
     */
    private static void generateDependenciesREADME(
            final String path,
            final String about
    ) {
        // 读取文件
        String content = null, dependencies = null;
        try {
            content = new String(FileUtils.readFileBytes(new File(path, "build.gradle")), DevFinal.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (content != null && content.indexOf("dependencies") != -1) {
            dependencies = content.substring(content.indexOf("dependencies"));
        }

        StringBuilder builder = new StringBuilder();
        builder.append(DevFinal.NEW_LINE_STR)
                .append("# About")
                .append(DevFinal.NEW_LINE_STR_X2)
                .append(about);
        if (StringUtils.isNotEmpty(dependencies)) {
            builder.append(DevFinal.NEW_LINE_STR_X2)
                    .append("# 依赖信息")
                    .append(DevFinal.NEW_LINE_STR_X2)
                    .append("```groovy")
                    .append(DevFinal.NEW_LINE_STR)
                    .append(dependencies)
                    .append(DevFinal.NEW_LINE_STR)
                    .append("```");
        }
        // 保存 AndroidManifest README
        generateAndroidManifestREADME(builder, path);
        // 保存 main 目录信息
        generateMainCatalogREADME(builder, path);
        // 插入文档尾部内容
        Utils.insertModuleTailREADME(builder, path);

        String readme = builder.toString();
        try {
            FileUtils.saveFile(new File(path, "README.md"), readme.getBytes(DevFinal.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成 AndroidManifest 信息
     * @param builder {@link StringBuilder}
     * @param path    文件路径
     */
    private static void generateAndroidManifestREADME(
            final StringBuilder builder,
            final String path
    ) {
        File manifestFile = new File(path, "src/main/AndroidManifest.xml");
        if (manifestFile.exists()) {
            // 读取文件
            String content = null;
            try {
                content = new String(FileUtils.readFileBytes(manifestFile), DevFinal.UTF_8);
            } catch (UnsupportedEncodingException e) {
            }
            if (StringUtils.isNotEmpty(content)) {
                builder.append(DevFinal.NEW_LINE_STR_X2)
                        .append("# AndroidManifest")
                        .append(DevFinal.NEW_LINE_STR_X2)
                        .append("```xml")
                        .append(DevFinal.NEW_LINE_STR)
                        .append(content)
                        .append(DevFinal.NEW_LINE_STR)
                        .append("```");
            }
        }
    }

    /**
     * 生成 main 目录信息
     * @param builder {@link StringBuilder}
     * @param path    文件路径
     */
    private static void generateMainCatalogREADME(
            final StringBuilder builder,
            final String path
    ) {
        File javaFile = new File(path, "src/main/java");
        if (javaFile.exists()) {
            String catalog = CatalogGenerate.generate(javaFile.getAbsolutePath()
                    , "java", new HashMap<>(), new ArrayList<>(), Integer.MAX_VALUE);
            if (StringUtils.isNotEmpty(catalog)) {
                catalog = catalog.replaceAll("\\| null", "");
                builder.append(DevFinal.NEW_LINE_STR_X2)
                        .append("# main/java 目录结构")
                        .append(DevFinal.NEW_LINE_STR_X2)
                        .append(catalog);
            }
        }

        File resFile = new File(path, "src/main/res");
        if (resFile.exists()) {
            String catalog = CatalogGenerate.generate(resFile.getAbsolutePath()
                    , "res", new HashMap<>(), new ArrayList<>(), Integer.MAX_VALUE);
            if (StringUtils.isNotEmpty(catalog)) {
                catalog = catalog.replaceAll("\\| null", "");
                builder.append(DevFinal.NEW_LINE_STR_X2)
                        .append("# main/res 目录结构")
                        .append(DevFinal.NEW_LINE_STR_X2)
                        .append(catalog);
            }
        }

        File   mainFile  = new File(path, "src/main");
        File[] mainFiles = mainFile.listFiles();
        if (mainFiles != null) {
            for (File mrFile : mainFiles) {
                String mainResFileName = mrFile.getName();
                if (mrFile.isDirectory() && StringUtils.isStartsWith(mainResFileName, "res-")) {
                    File mainResFile = new File(path, "src/main/" + mainResFileName);
                    if (mainResFile.exists()) {
                        String catalog = CatalogGenerate.generate(mainResFile.getAbsolutePath()
                                , mainResFileName, new HashMap<>(), new ArrayList<>(), Integer.MAX_VALUE);
                        if (StringUtils.isNotEmpty(catalog)) {
                            catalog = catalog.replaceAll("\\| null", "");
                            builder.append(DevFinal.NEW_LINE_STR_X2)
                                    .append("# main/").append(mainResFileName).append(" 目录结构")
                                    .append(DevFinal.NEW_LINE_STR_X2)
                                    .append(catalog);
                        }
                    }
                }
            }
        }

        File assetsFile = new File(path, "src/main/assets");
        if (assetsFile.exists()) {
            String catalog = CatalogGenerate.generate(assetsFile.getAbsolutePath()
                    , "assets", new HashMap<>(), new ArrayList<>(), Integer.MAX_VALUE);
            if (StringUtils.isNotEmpty(catalog)) {
                catalog = catalog.replaceAll("\\| null", "");
                builder.append(DevFinal.NEW_LINE_STR_X2)
                        .append("# main/assets 目录结构")
                        .append(DevFinal.NEW_LINE_STR_X2)
                        .append(catalog);
            }
        }

        File libsFile = new File(path, "/libs");
        if (libsFile.exists()) {
            String        libsPath    = libsFile.getAbsolutePath();
            List<File>    lists       = FileUtils.listFilesInDir(libsFile, true);
            List<String>  listNames   = new ArrayList<>();
            StringBuilder libsBuilder = new StringBuilder();
            for (File file : lists) {
                String filePath = file.getAbsolutePath();
                String tempPath = new File(libsFile, file.getName()).getAbsolutePath();
                if (filePath.equals(tempPath)) {
                    if (file.isFile()) {
                        filePath = null;
                        listNames.add(file.getName());
                    } else {
                        filePath = String.format("【%s】", file.getName());
                    }
                } else {
                    filePath = filePath.replace(libsPath, "");
                }
                if (filePath != null) {
                    libsBuilder.append(filePath).append(DevFinal.NEW_LINE_STR_X2);
                }
            }
            if (listNames.size() != 0) {
                libsBuilder.append("【libs - root】").append(DevFinal.NEW_LINE_STR_X2);
                for (String name : listNames) {
                    libsBuilder.append(name).append(DevFinal.NEW_LINE_STR_X2);
                }
            }
            builder.append(DevFinal.NEW_LINE_STR)
                    .append("# main/libs 目录结构")
                    .append(DevFinal.NEW_LINE_STR_X2)
                    .append(libsBuilder);
        }
    }

    // ===========
    // = 插入文档 =
    // ===========

    /**
     * 插入文档头部内容
     * @param builder 待插入 {@link StringBuilder}
     * @param path    文件路径 ( 模块 )
     */
    public static void insertHeadREADME(
            final StringBuilder builder,
            final String path
    ) {
        if (StringUtils.equals(path, Config.CORE_LOCAL_PATH)) {
            builder.append("# About");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("该目录属于核心基础库代码，整个组件化项目基于该基础上进行开发");
            builder.append(DevFinal.NEW_LINE_STR);
        } else if (StringUtils.equals(path, Config.MODULE_LOCAL_PATH)) {
            builder.append("# About");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("该目录下的 Module 在 `isModular=true` 的情况下，都属于独立的应用可单独运行");
            builder.append("，为 `false` 则都属于功能模块，被主体应用 ( 壳 ) 所依赖使用");
            builder.append(DevFinal.NEW_LINE_STR);
        } else if (StringUtils.equals(path, Config.LIBS_LOCAL_PATH)) {
            builder.append("# About");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("该目录属于 项目模块快捷工具封装复用、第三方库 clone 对源码进行差异化修改使用等存储目录");
            builder.append(DevFinal.NEW_LINE_STR);
        } else if (StringUtils.equals(path, Config.INTERESTING_LOCAL_PATH)) {
            builder.append("# About");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("该目录主要存储一些有趣的试验、代码生成、规范检测项目");
            builder.append(DevFinal.NEW_LINE_STR);
        }
    }

    /**
     * 插入文档尾部内容
     * @param builder 待插入 {@link StringBuilder}
     * @param path    文件路径 ( 模块 )
     */
    public static void insertTailREADME(
            final StringBuilder builder,
            final String path
    ) {
        if (StringUtils.equals(path, Config.CORE_LOCAL_PATH)) {
            builder.append(DevFinal.NEW_LINE_STR);
            builder.append("# core/core");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("> 该 Module 依赖 core 核心开发库、核心第三方库等");
            builder.append(DevFinal.NEW_LINE_STR).append(">").append(DevFinal.NEW_LINE_STR);
            builder.append("> 统一维护核心库依赖，对外只需要依赖该 Module 便可使用整个核心模块 ( core 文件以及内部所有 libs )");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("## core/core_base_lib");
            builder.append(DevFinal.NEW_LINE_STR_X2);
            builder.append("> 该 Module 基于 Dev 系列开发库搭建，且不存在任何代码属于核心 lib 依赖 ( 全部开发基于该 module )");
            builder.append(DevFinal.NEW_LINE_STR).append(">").append(DevFinal.NEW_LINE_STR);
            builder.append("> 用于统一维护基础核心开发库依赖，如有必须依赖底层库在此添加");
        }
    }

    /**
     * 插入文档尾部内容 ( 各个 lib、module )
     * @param builder 待插入 {@link StringBuilder}
     * @param path    文件路径 ( 模块 )
     */
    public static void insertModuleTailREADME(
            final StringBuilder builder,
            final String path
    ) {
        // 属于 lib_circle_igview lib
        if (path.indexOf("lib_circle_igview") != -1) {
            builder.append(DevFinal.NEW_LINE_STR)
                    .append("# Usage").append(DevFinal.NEW_LINE_STR_X2)
                    .append("```xml").append(DevFinal.NEW_LINE_STR)
                    .append("<de.hdodenhof.circleimageview.CircleImageView")
                    .append(DevFinal.NEW_LINE_STR)
                    .append("\txmlns:app=\"http://schemas.android.com/apk/res-auto\"")
                    .append(DevFinal.NEW_LINE_STR)
                    .append("\tandroid:id=\"@+id/profile_image\"")
                    .append(DevFinal.NEW_LINE_STR)
                    .append("\tandroid:layout_width=\"96dp\"")
                    .append(DevFinal.NEW_LINE_STR)
                    .append("\tandroid:layout_height=\"96dp\"")
                    .append(DevFinal.NEW_LINE_STR)
                    .append("\tandroid:src=\"@drawable/profile\"")
                    .append(DevFinal.NEW_LINE_STR)
                    .append("\tapp:civ_border_width=\"2dp\"")
                    .append(DevFinal.NEW_LINE_STR)
                    .append("\tapp:civ_border_color=\"#FF000000\"/>")
                    .append(DevFinal.NEW_LINE_STR)
                    .append("```");
        }
    }
}
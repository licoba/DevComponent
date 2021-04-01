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
        Config.insertHeadREADME(path, builder);
        // 文档内容
        builder.append(DevFinal.NEW_LINE_STR)
                .append("## 目录结构")
                .append(DevFinal.NEW_LINE_STR_X2)
                .append(catalog);
        // 插入文档尾部内容
        Config.insertTailREADME(path, builder);
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
        String content = null;
        try {
            content = new String(FileUtils.readFileBytes(new File(path, "build.gradle")), DevFinal.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String dependencies = content.substring(content.indexOf("dependencies"));

        StringBuilder builder = new StringBuilder();
        builder.append(DevFinal.NEW_LINE_STR)
                .append("# About")
                .append(DevFinal.NEW_LINE_STR_X2)
                .append(about)
                .append(DevFinal.NEW_LINE_STR_X2)
                .append("# 依赖信息")
                .append(DevFinal.NEW_LINE_STR_X2)
                .append("```groovy")
                .append(DevFinal.NEW_LINE_STR)
                .append(dependencies)
                .append(DevFinal.NEW_LINE_STR)
                .append("```");
        // 保存 AndroidManifest README
        generateAndroidManifestREADME(builder, path);
        // 保存 main 目录信息
        generateMainCatalogREADME(builder, path);

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
            } catch (Exception e) {
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
    }
}
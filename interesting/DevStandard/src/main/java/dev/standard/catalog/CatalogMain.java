package dev.standard.catalog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import dev.utils.DevFinal;
import dev.utils.common.FileUtils;
import dev.utils.common.StringUtils;

/**
 * detail: 目录生成 Main 方法
 * @author Ttt
 */
final class CatalogMain {

    public static void main(String[] args) {

        // 生成 interesting 目录结构
        generateCatalog(Config.INTERESTING_LOCAL_PATH, Config.INTERESTING_DIR_NAME, Config.sInterestingCatalogMap,
                null, null, 0, false);

        // 生成 core 目录结构
        generateCatalog(Config.CORE_LOCAL_PATH, Config.CORE_DIR_NAME, Config.sCoreCatalogMap,
                Config.sCoreAboutMap, Config.sCoreIgnoreCatalogs, 1, true);

        // 生成 module 目录结构
        generateCatalog(Config.MODULE_LOCAL_PATH, Config.MODULE_DIR_NAME, Config.sModuleCatalogMap,
                Config.sModuleAboutMap, Config.sModuleIgnoreCatalogs, 0, true);

        // 生成 libs 目录结构
        generateCatalog(Config.LIBS_LOCAL_PATH, Config.LIBS_DIR_NAME, Config.sLibsCatalogMap,
                Config.sLibsAboutMap, Config.sLibsIgnoreCatalogs, 0, true);
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
    private static void generateCatalog(
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
        // 读取文件
        String content = null;
        try {
            content = new String(FileUtils.readFileBytes(new File(path, "src/main/AndroidManifest.xml")), DevFinal.UTF_8);
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
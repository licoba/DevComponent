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
        // 生成 core 目录结构
        generateCatalog(Config.CORE_LOCAL_PATH, Config.CORE_DIR_NAME, Config.sCoreCatalogMap,
                Config.sCoreIgnoreCatalogs, 1, true);
    }

    /**
     * 生成目录文件
     * @param path              文件路径
     * @param dirName           文件名
     * @param mapCatalog        对应目录注释
     * @param listIgnoreCatalog 忽略目录
     * @param layer             目录层级
     */
    private static void generateCatalog(
            final String path,
            final String dirName,
            final Map<String, String> mapCatalog,
            final List<String> listIgnoreCatalog,
            final int layer,
            final boolean generateDependenciesCatalog
    ) {
        String        catalog = CatalogGenerate.generate(path, dirName, mapCatalog, listIgnoreCatalog, layer);
        StringBuilder builder = new StringBuilder();
        builder.append(DevFinal.NEW_LINE_STR)
                .append("## 目录结构")
                .append(DevFinal.NEW_LINE_STR_X2)
                .append(catalog);
        Config.insertREADME(path, builder);
        String readme = StringUtils.clearEndsWith(builder.toString(), "\n");
        try {
            FileUtils.saveFile(new File(path, "README.md"), readme.getBytes(DevFinal.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (generateDependenciesCatalog) {
            generateDependenciesCatalog(path, "", mapCatalog);
        }
    }

    /**
     * 生成依赖目录文件
     * @param path       文件路径
     * @param dirName    文件名
     * @param mapCatalog 对应目录注释
     */
    private static void generateDependenciesCatalog(
            final String path,
            final String dirName,
            final Map<String, String> mapCatalog
    ) {
        File     root  = new File(path, dirName.replaceAll("\\.", "//"));
        String[] names = root.list();
        for (String name : names) {
            String catalog     = dirName + "." + name;
            String catelogPath = path + catalog.replaceAll("\\.", "//") + File.separator;
            if (mapCatalog.containsKey(catalog)) {
                File gradleFile = new File(catelogPath + "build.gradle");
                // 文件存在则进行生成
                if (gradleFile.exists()) {
                    generateDependenciesREADME(catelogPath, mapCatalog.get(catalog));
                } else {
                    String dir = ".";
                    if (StringUtils.isNotEmpty(dirName)) {
                        dir += dirName + ".";
                        dir = "." + StringUtils.clearStartsWith(dir, ".");
                    }
                    generateDependenciesCatalog(path, dir + name, mapCatalog);
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
        String readme = builder.toString();
        try {
            FileUtils.saveFile(new File(path, "README.md"), readme.getBytes(DevFinal.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
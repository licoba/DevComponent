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
        createCatalog(Config.CORE_LOCAL_PATH, Config.CORE_DIR_NAME, Config.sCoreCatalogMap, Config.sCoreIgnoreCatalogs, 1);
    }

    /**
     * 创建目录
     * @param path              文件路径
     * @param dirName           文件名
     * @param mapCatalog        对应目录注释
     * @param listIgnoreCatalog 忽略目录
     * @param layer             目录层级
     */
    private static void createCatalog(
            final String path,
            final String dirName,
            final Map<String, String> mapCatalog,
            final List<String> listIgnoreCatalog,
            final int layer
    ) {
        String        catalog = CatalogGenerate.generate(path, dirName, mapCatalog, listIgnoreCatalog, layer);
        StringBuilder builder = new StringBuilder();
        builder.append(DevFinal.NEW_LINE_STR)
                .append("## 目录结构")
                .append(DevFinal.NEW_LINE_STR_X2)
                .append(catalog);
        String readme = StringUtils.clearEndsWith(builder.toString(), "\n");
        try {
            FileUtils.saveFile(new File(path, "README.md"), readme.getBytes(DevFinal.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
package dev.standard.pangu;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dev.utils.JCLogUtils;
import dev.utils.common.FileIOUtils;
import dev.utils.common.FileUtils;
import dev.utils.common.MapUtils;
import dev.utils.common.StringUtils;

/**
 * detail: 全局检测处理
 * @author Ttt
 */
final class PanguAnalyzeMain {

    // 代码间距等规范处理
    private static final Pangu                     sPangu                   = new Pangu();
    // 判断是否覆盖文件内容
    private static final boolean                   sCoverText               = true;
    // 代码注释空格间距异常记录
    private static final Map<String, String>       sAnnotationSpaceMap      = new HashMap<>();
    // 代码注释重复换行记录
    private static final Map<String, List<String>> sAnnotationRepeatLineMap = new HashMap<>();

    public static void main(String[] args) {
        String path = "";

        List<File> lists = getFileCatalogLists(path);
        forReader(lists, new Filter() {
            @Override
            public boolean filter(File file) {
                // 获取完整路径
                String absolutePath = file.getAbsolutePath();
//                        && absolutePath.endsWith(".properties")
//                        && absolutePath.endsWith(".pro")
//                        && absolutePath.endsWith(".gradle")
//                        && absolutePath.endsWith(".gradle")
//                        && absolutePath.endsWith(".java")

                return absolutePath.indexOf("\\.git") != -1
                        || absolutePath.indexOf("\\.idea") != -1
                        || absolutePath.indexOf("\\.gradlew") != -1
                        || absolutePath.indexOf("\\wrapper") != -1
                        || absolutePath.indexOf("\\.gradle") != -1
                        || absolutePath.indexOf("\\build") != -1
                        || FileUtils.isAudioFormats(file)
                        || FileUtils.isVideoFormats(file)
                        || FileUtils.isImageFormats(file)
                        || absolutePath.endsWith("LICENSE")
                        || absolutePath.endsWith(".zip")
                        || absolutePath.endsWith(".rar")
                        || absolutePath.endsWith(".iml")
                        && absolutePath.endsWith(".xml");
            }
        });
        System.out.println("处理结束");

        // 统一拼接打印数据
        Map<String, Map<String, List<String>>> printMap = new LinkedHashMap<>();
        printMap.put("1.代码注释重复换行记录", sAnnotationRepeatLineMap);
        // 转换 JSON 数据
        String mapJSON = toJsonFormat(printMap, true);
        System.out.println(mapJSON);
    }

    /**
     * detail: 过滤处理
     * @author Ttt
     */
    private interface Filter {

        /**
         * 过滤文件处理
         * @param file {@link File}
         * @return 是否过滤该文件
         */
        boolean filter(File file);
    }

    // ===============
    // = 内部处理方法 =
    // ===============

    /**
     * 获取文件目录列表
     * @param path 文件路径
     * @return 文件目录列表
     */
    private static List<File> getFileCatalogLists(final String path) {
        List<File> lists = new ArrayList<>();
        try {
            // 获取文件路径
            File baseFile = new File(path);
            // 获取子文件
            File[] files = baseFile.listFiles();
            for (File file : files) {
                lists.add(file);
            }
        } catch (Exception e) {
            JCLogUtils.e("getFileCatalogLists", e);
        }
        return lists;
    }

    /**
     * 循环读取处理
     * @param lists  文件列表
     * @param filter 过滤处理
     */
    private static void forReader(
            final List<File> lists,
            final Filter filter
    ) {
        for (File file : lists) {
            if (file.isDirectory() && !filter.filter(file)) {
                forReader(getFileCatalogLists(file.getAbsolutePath() + "/"), filter);
            } else {
                if (!filter.filter(file)) {
                    System.out.println("处理: " + file.getAbsolutePath());
                    // = 慎用, 需要仔细对比部分代码差异化, 如 " " 会被替换成 "" 等 =
                    // 读取文件内容
                    String text = FileIOUtils.readFileToString(file, null);
                    if (text != null) {
                        // 处理后的代码
                        String newText = sPangu.spacingText(text);
                        newText = sPangu.spacingText(newText); // 多次处理
                        // 判断一样
                        if (!newText.equals(text)) {
                            sAnnotationSpaceMap.put(file.getName(), newText);
                            // 不一样才覆盖
                            if (sCoverText) {
                                FileUtils.saveFile(file, StringUtils.getBytes(newText));
                            }
                        }
                    }
                    // 读取文件
                    readFile(file);
                }
            }
        }
    }

    /**
     * 读取文件
     * @param file 文件
     */
    private static void readFile(final File file) {
        // 读取文件内容
        List<String> lists = FileIOUtils.readFileToList(file, 0, Integer.MAX_VALUE);
        if (lists != null) {
            // 判断是否需要判断 重复出现情况
            boolean repeat = false;
            // 循环判断
            for (int i = 0, len = lists.size(); i < len; i++) {
                // 获取每一行代码
                String code = lists.get(i);
                // 判断是否 null
                boolean isSpace = StringUtils.isSpace(code);
                // 防止为 null
                if (!isSpace) {
                    repeat = false; // 不需要判断重复
                } else {
                    if (code != null && repeat) {
                        MapUtils.putToList(sAnnotationRepeatLineMap, file.getName(), String.valueOf(i + 1));
                    }
                    // 表示需要检测重复
                    repeat = true;
                }
            }
        }
    }

    // ========
    // = Gson =
    // ========

    /**
     * 创建 GsonBuilder
     * @param serializeNulls 是否序列化 null 值
     * @return {@link GsonBuilder}
     */
    private static GsonBuilder createGson(final boolean serializeNulls) {
        final GsonBuilder builder = new GsonBuilder();
        if (serializeNulls) builder.serializeNulls();
        return builder;
    }

    /**
     * 转换 JSON 格式数据, 并且格式化
     * @param data         待转换对象
     * @param includeNulls 是否序列化 null 值
     * @return 格式化 JSON 数据
     */
    private static String toJsonFormat(
            final Object data,
            final boolean includeNulls
    ) {
        if (data != null) {
            try {
                // 返回 JSON 格式数据 ( 格式化 )
                return createGson(includeNulls).setPrettyPrinting().create().toJson(data);
            } catch (Exception e) {
                JCLogUtils.e("toJsonFormat", e);
            }
        }
        return "";
    }
}
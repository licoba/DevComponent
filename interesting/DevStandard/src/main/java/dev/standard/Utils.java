package dev.standard;

import com.google.gson.GsonBuilder;

import dev.utils.JCLogUtils;

/**
 * detail: 内部工具类
 * @author Ttt
 */
public final class Utils {

    private Utils() {
    }

    static {
        JCLogUtils.setPrintLog(true);
        JCLogUtils.setControlPrintLog(true);
//        JCLogUtils.setPrint(new JCLogUtils.Print() {
//            @Override
//            public void printLog(int logType, String tag, String message) {
//                System.out.println(tag + " : " + message);
//            }
//        });
    }

    // ========
    // = Gson =
    // ========

    /**
     * 创建 GsonBuilder
     * @param serializeNulls 是否序列化null值
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
     * @param includeNulls 是否序列化null值
     * @return 格式化 JSON 数据
     */
    public static String toJsonFormat(
            final Object data,
            final boolean includeNulls
    ) {
        if (data != null) {
            try {
                // 返回 JSON格式数据 - 格式化
                return createGson(includeNulls).setPrettyPrinting().create().toJson(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
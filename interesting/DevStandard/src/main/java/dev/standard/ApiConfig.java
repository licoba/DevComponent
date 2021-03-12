package dev.standard;

import java.io.File;

/**
 * detail: 配置信息
 * @author Ttt
 */
public final class ApiConfig {

    private ApiConfig() {
    }

    // 项目路径
    public static final String PROJECT_PATH = new File(System.getProperty("user.dir")).getAbsolutePath();
}
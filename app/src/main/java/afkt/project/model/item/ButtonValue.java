package afkt.project.model.item;

import dev.utils.app.ResourceUtils;

/**
 * detail: Button Value 实体类
 * @author Ttt
 */
public class ButtonValue {

    // 按钮类型
    public int    type;
    // 文案
    public String text;

    public ButtonValue(
            int type,
            int id
    ) {
        this(type, ResourceUtils.getString(id));
    }

    public ButtonValue(
            int type,
            String text
    ) {
        this.type = type;
        this.text = text;
    }

    // ========
    // = 常量 =
    // ========

    private static final int BASE                   = 1001;
    // 架构
    public static final  int MODULE_FRAMEWORK       = BASE + 10000;
    // Lib
    public static final  int MODULE_LIB             = BASE + 20000;
    // UI
    public static final  int MODULE_UI              = BASE + 30000;
    // 其他功能
    public static final  int MODULE_OTHER           = BASE + 40000;
    // DevWidget UI 库
    public static final  int MODULE_DEV_WIDGET      = BASE + 50000;
    // DevEnvironment 环境配置切换库
    public static final  int MODULE_DEV_ENVIRONMENT = BASE + 60000;

    // =============
    // = Framework =
    // =============

    // MVP
    public static final int BTN_MVP  = MODULE_FRAMEWORK;
    // MVVM
    public static final int BTN_MVVM = BTN_MVP + 100;

    // =======
    // = Lib =
    // =======

    // EventBusUtils
    public static final int BTN_EVENT_BUS          = MODULE_LIB;
    // Register
    public static final int BTN_EVENT_REGISTER     = BTN_EVENT_BUS + 1;
    // unRegister
    public static final int BTN_EVENT_UNREGISTER   = BTN_EVENT_BUS + 2;
    // 清空粘性事件
    public static final int BTN_EVENT_CLEAN_STICKY = BTN_EVENT_BUS + 3;
    // 发送事件
    public static final int BTN_EVENT_SEND         = BTN_EVENT_BUS + 4;
    // 发送粘性事件
    public static final int BTN_EVENT_SEND_STICKY  = BTN_EVENT_BUS + 5;

    // GlideUtils
    public static final int BTN_GLIDE = MODULE_LIB + 1;

    // ImageLoaderUtils
    public static final int BTN_IMAGE_LOADER = MODULE_LIB + 2;

    // GsonUtils
    public static final int BTN_GSON = MODULE_LIB + 3;

    // FastjsonUtils
    public static final int BTN_FASTJSON = MODULE_LIB + 4;

    // ZXingQRCodeUtils
    public static final int BTN_ZXING = MODULE_LIB + 5;

    // PictureSelectorUtils
    public static final int BTN_PICTURE_SELECTOR = MODULE_LIB + 6;

    // OkGoUtils
    public static final int BTN_OKGO = MODULE_LIB + 7;

    // LubanUtils
    public static final int BTN_LUBAN = MODULE_LIB + 8;

    // MMKVUtils
    public static final int BTN_MMKV = MODULE_LIB + 9;

    // GreenDAO
    public static final int BTN_GREEN_DAO = MODULE_LIB + 10;

    // Room
    public static final int BTN_ROOM = MODULE_LIB + 11;

    // DataStore
    public static final int BTN_DATA_STORE = MODULE_LIB + 12;

    // WorkManagerUtils
    public static final int BTN_WORK_MANAGER = MODULE_LIB + 13;

    // ======
    // = UI =
    // ======

    // ToastTint ( 着色美化 Toast )
    public static final int BTN_TOAST_TINT              = MODULE_UI;
    // Toast Success
    public static final int BTN_TOAST_TINT_SUCCESS      = BTN_TOAST_TINT + 1;
    // Toast Error
    public static final int BTN_TOAST_TINT_ERROR        = BTN_TOAST_TINT + 2;
    // Toast Info
    public static final int BTN_TOAST_TINT_INFO         = BTN_TOAST_TINT + 3;
    // Toast Normal
    public static final int BTN_TOAST_TINT_NORMAL       = BTN_TOAST_TINT + 4;
    // Toast Warning
    public static final int BTN_TOAST_TINT_WARNING      = BTN_TOAST_TINT + 5;
    // Toast Custom Style
    public static final int BTN_TOAST_TINT_CUSTOM_STYLE = BTN_TOAST_TINT + 6;

    // 常见 UI、GradientDrawable 效果等
    public static final int BTN_UI_EFFECT = MODULE_UI + 100;

    // 点击 显示/隐藏 ( 状态栏 )
    public static final int BTN_STATUS_BAR = MODULE_UI + 200;

    // 计算字体宽度、高度
    public static final int BTN_TEXT_CALC = MODULE_UI + 300;

    // Adapter Item EditText 输入监听
    public static final int BTN_ADAPTER_EDITS = MODULE_UI + 400;

    // 多选辅助类 MultiSelectAssist
    public static final int BTN_MULTI_SELECT = MODULE_UI + 500;

    // GPU ACV 文件滤镜效果
    public static final int BTN_GPU_ACV = MODULE_UI + 600;

    // GPU 滤镜效果
    public static final int BTN_GPU_FILTER = MODULE_UI + 700;

    // 创建二维码
    public static final int BTN_QRCODE_CREATE = MODULE_UI + 800;

    // 二维码图片解析
    public static final int BTN_QRCODE_IMAGE = MODULE_UI + 900;

    // 二维码扫描解析
    public static final int BTN_QRCODE_SCAN = MODULE_UI + 1000;

    // CapturePictureUtils 截图工具类
    public static final int BTN_CAPTURE_PICTURE = MODULE_UI + 1100;

    // 两个 TextView 显示效果
    public static final int BTN_TEXTVIEW = MODULE_UI + 1200;

    // RecyclerView 吸附效果
    public static final int BTN_ITEM_STICKY = MODULE_UI + 1300;

    // RecyclerView 滑动删除、上下滑动
    public static final int BTN_RECY_ITEM_SLIDE = MODULE_UI + 1400;

    // LinearSnapHelper - RecyclerView
    public static final int BTN_RECY_LINEAR_SNAP = MODULE_UI + 1500;

    // LinearSnapHelper - 无限滑动
    public static final int BTN_RECY_LINEAR_SNAP_MAX = MODULE_UI + 1600;

    // PagerSnapHelper - RecyclerView
    public static final int BTN_RECY_PAGER_SNAP = MODULE_UI + 1700;

    // PagerSnapHelper - 无限滑动
    public static final int BTN_RECY_PAGER_SNAP_MAX = MODULE_UI + 1800;

    // Material ShapeableImageView
    public static final int BTN_SHAPEABLE_IMAGE_VIEW = MODULE_UI + 1900;

    // Material BottomSheet
    public static final int BTN_BOTTOM_SHEET = MODULE_UI + 2000;

    // Material BottomSheetDialog
    public static final int BTN_BOTTOM_SHEET_DIALOG = MODULE_UI + 2100;

    // Palette 调色板
    public static final int BTN_PALETTE = MODULE_UI + 2200;

    // Flexbox LayoutManager
    public static final int BTN_FLEXBOX_LAYOUTMANAGER = MODULE_UI + 2300;

    // Material Chip、ChipGroups、ChipDrawable
    public static final int BTN_CHIP = MODULE_UI + 2400;

    // ViewPager2
    public static final int BTN_VIEWPAGER2 = MODULE_UI + 2500;

    // ===========
    // = 其他功能 =
    // ===========

    // 事件 / 广播监听 ( 网络状态、屏幕旋转等 )
    public static final int BTN_LISTENER           = MODULE_OTHER;
    // Wifi 监听
    public static final int BTN_WIFI_LISTENER      = BTN_LISTENER + 1;
    // 网络监听
    public static final int BTN_NETWORK_LISTENER   = BTN_LISTENER + 2;
    // 电话监听
    public static final int BTN_PHONE_LISTENER     = BTN_LISTENER + 3;
    // 短信监听
    public static final int BTN_SMS_LISTENER       = BTN_LISTENER + 4;
    // 时区、时间监听
    public static final int BTN_TIME_LISTENER      = BTN_LISTENER + 5;
    // 屏幕监听
    public static final int BTN_SCREEN_LISTENER    = BTN_LISTENER + 6;
    // 屏幕旋转监听 ( 重力传感器 )
    public static final int BTN_ROTA_LISTENER      = BTN_LISTENER + 7;
    // 屏幕旋转监听 ( OrientationEventListener )
    public static final int BTN_ROTA2_LISTENER     = BTN_LISTENER + 8;
    // 电量监听
    public static final int BTN_BATTERY_LISTENER   = BTN_LISTENER + 9;
    // 应用状态监听
    public static final int BTN_APP_STATE_LISTENER = BTN_LISTENER + 10;

    // 通知栏监听服务 ( NotificationService )
    public static final int BTN_NOTIFICATION_SERVICE            = MODULE_OTHER + 100;
    // 检查是否开启
    public static final int BTN_NOTIFICATION_SERVICE_CHECK      = BTN_NOTIFICATION_SERVICE + 1;
    // 开始监听
    public static final int BTN_NOTIFICATION_SERVICE_REGISTER   = BTN_NOTIFICATION_SERVICE + 2;
    // 注销监听
    public static final int BTN_NOTIFICATION_SERVICE_UNREGISTER = BTN_NOTIFICATION_SERVICE + 3;

    // 无障碍监听服务 ( AccessibilityListenerService )
    public static final int BTN_ACCESSIBILITY_SERVICE            = MODULE_OTHER + 200;
    // 检查是否开启
    public static final int BTN_ACCESSIBILITY_SERVICE_CHECK      = BTN_ACCESSIBILITY_SERVICE + 1;
    // 开始监听
    public static final int BTN_ACCESSIBILITY_SERVICE_REGISTER   = BTN_ACCESSIBILITY_SERVICE + 2;
    // 注销监听
    public static final int BTN_ACCESSIBILITY_SERVICE_UNREGISTER = BTN_ACCESSIBILITY_SERVICE + 3;

    // Wifi 相关 ( 热点 )
    public static final int BTN_WIFI                     = MODULE_OTHER + 300;
    // 打开 Wifi
    public static final int BTN_WIFI_OPEN                = BTN_WIFI + 1;
    // 关闭 Wifi
    public static final int BTN_WIFI_CLOSE               = BTN_WIFI + 2;
    // 打开 Wifi 热点
    public static final int BTN_WIFI_HOT_OPEN            = BTN_WIFI + 3;
    // 关闭 Wifi 热点
    public static final int BTN_WIFI_HOT_CLOSE           = BTN_WIFI + 4;
    // 注册 Wifi 监听
    public static final int BTN_WIFI_LISTENER_REGISTER   = BTN_WIFI + 5;
    // 注销 Wifi 监听
    public static final int BTN_WIFI_LISTENER_UNREGISTER = BTN_WIFI + 6;

    // 铃声、震动、通知栏等功能
    public static final int BTN_FUNCTION                      = MODULE_OTHER + 400;
    // 震动
    public static final int BTN_FUNCTION_VIBRATE              = BTN_FUNCTION + 1;
    // 铃声 - 播放一小段音频
    public static final int BTN_FUNCTION_BEEP                 = BTN_FUNCTION + 2;
    // 是否存在通知权限
    public static final int BTN_FUNCTION_NOTIFICATION_CHECK   = BTN_FUNCTION + 3;
    // 开启通知权限
    public static final int BTN_FUNCTION_NOTIFICATION_OPEN    = BTN_FUNCTION + 4;
    // 通知消息
    public static final int BTN_FUNCTION_NOTIFICATION         = BTN_FUNCTION + 5;
    // 移除消息
    public static final int BTN_FUNCTION_NOTIFICATION_REMOVE  = BTN_FUNCTION + 6;
    // 回到桌面
    public static final int BTN_FUNCTION_HOME                 = BTN_FUNCTION + 7;
    // 打开手电筒
    public static final int BTN_FUNCTION_FLASHLIGHT_OPEN      = BTN_FUNCTION + 8;
    // 关闭手电筒
    public static final int BTN_FUNCTION_FLASHLIGHT_CLOSE     = BTN_FUNCTION + 9;
    // 是否创建桌面快捷方式
    public static final int BTN_FUNCTION_SHORTCUT_CHECK       = BTN_FUNCTION + 10;
    // 创建桌面快捷方式
    public static final int BTN_FUNCTION_SHORTCUT_CREATE      = BTN_FUNCTION + 11;
    // 删除桌面快捷方式
    public static final int BTN_FUNCTION_SHORTCUT_DELETE      = BTN_FUNCTION + 12;
    // 打印内存信息
    public static final int BTN_FUNCTION_MEMORY_PRINT         = BTN_FUNCTION + 13;
    // 打印设备信息
    public static final int BTN_FUNCTION_DEVICE_PRINT         = BTN_FUNCTION + 14;
    // 跳转到 APP 设置详情页面
    public static final int BTN_FUNCTION_APP_DETAILS_SETTINGS = BTN_FUNCTION + 15;
    // 打开 GPS 设置界面
    public static final int BTN_FUNCTION_GPS_SETTINGS         = BTN_FUNCTION + 16;
    // 打开网络设置界面
    public static final int BTN_FUNCTION_WIRELESS_SETTINGS    = BTN_FUNCTION + 17;
    // 跳转到系统设置页面
    public static final int BTN_FUNCTION_SYS_SETTINGS         = BTN_FUNCTION + 18;

    // TimerManager 定时器工具类
    public static final int BTN_TIMER            = MODULE_OTHER + 500;
    // 启动定时器
    public static final int BTN_TIMER_START      = BTN_TIMER + 1;
    // 停止定时器
    public static final int BTN_TIMER_STOP       = BTN_TIMER + 2;
    // 重新启动定时器
    public static final int BTN_TIMER_RESTART    = BTN_TIMER + 3;
    // 定时器是否启动
    public static final int BTN_TIMER_CHECK      = BTN_TIMER + 4;
    // 获取定时器
    public static final int BTN_TIMER_GET        = BTN_TIMER + 5;
    // 获取运行次数
    public static final int BTN_TIMER_GET_NUMBER = BTN_TIMER + 6;

    // DevCache 缓存工具类
    public static final int BTN_CACHE             = MODULE_OTHER + 600;
    // 存储字符串
    public static final int BTN_CACHE_STRING      = BTN_CACHE + 1;
    // 存储有效期字符串
    public static final int BTN_CACHE_STRING_TIME = BTN_CACHE + 2;
    // 获取字符串
    public static final int BTN_CACHE_STRING_GET  = BTN_CACHE + 3;
    // 存储实体类
    public static final int BTN_CACHE_BEAN        = BTN_CACHE + 4;
    // 存储有效期实体类
    public static final int BTN_CACHE_BEAN_TIME   = BTN_CACHE + 5;
    // 获取实体类
    public static final int BTN_CACHE_BEAN_GET    = BTN_CACHE + 6;
    // 存储到指定位置
    public static final int BTN_CACHE_FILE        = BTN_CACHE + 7;
    // 获取指定位置缓存数据
    public static final int BTN_CACHE_FILE_GET    = BTN_CACHE + 8;
    // 清除全部数据
    public static final int BTN_CACHE_CLEAR       = BTN_CACHE + 9;

    // DevLogger 日志工具类
    public static final int BTN_LOGGER       = MODULE_OTHER + 700;
    // 打印日志
    public static final int BTN_LOGGER_PRINT = BTN_LOGGER + 1;
    // 打印日志耗时测试
    public static final int BTN_LOGGER_TIME  = BTN_LOGGER + 2;

    // 日志、异常文件记录保存
    public static final int BTN_FILE_RECORD          = MODULE_OTHER + 800;
    // AnalysisRecordUtils 工具类
    public static final int BTN_FILE_RECORD_ANALYSIS = BTN_FILE_RECORD + 1;
    // FileRecordUtils 工具类
    public static final int BTN_FILE_RECORD_UTILS    = BTN_FILE_RECORD + 2;

    // 奔溃日志捕获
    public static final int BTN_CRASH             = MODULE_OTHER + 900;
    // 点击崩溃捕获信息
    public static final int BTN_CRASH_CLICK_CATCH = BTN_CRASH + 1;

    // 路径信息
    public static final int BTN_PATH              = MODULE_OTHER + 1000;
    // 内部存储路径
    public static final int BTN_PATH_INTERNAL     = BTN_PATH + 1;
    // 应用外部存储路径
    public static final int BTN_PATH_APP_EXTERNAL = BTN_PATH + 2;
    // 外部存储路径 ( SDCard )
    public static final int BTN_PATH_SDCARD       = BTN_PATH + 3;

    // WebView 辅助类
    public static final int BTN_WEBVIEW = MODULE_OTHER + 1100;

    // startActivityForResult Callback
    public static final int BTN_ACTIVITY_RESULT_CALLBACK = MODULE_OTHER + 1200;

    // 添加联系人
    public static final int BTN_ADD_CONTACT = MODULE_OTHER + 1300;

    // 手机壁纸
    public static final int BTN_WALLPAPER = MODULE_OTHER + 1400;

    // ===================
    // = DevWidget UI 库 =
    // ===================

    // DevWidget
    public static final int BTN_DEV_WIDGET = MODULE_DEV_WIDGET;

    // ViewPager 滑动监听、控制滑动
    public static final int BTN_VIEW_PAGER = BTN_DEV_WIDGET + 100;

    // 自定义 ProgressBar 样式 View
    public static final int BTN_CUSTOM_PROGRESS_BAR = BTN_DEV_WIDGET + 200;

    // 自定义扫描 View ( QRCode、AR )
    public static final int BTN_SCAN_VIEW = BTN_DEV_WIDGET + 300;

    // 自动换行 View
    public static final int BTN_WRAP_VIEW = BTN_DEV_WIDGET + 400;

    // 签名 View
    public static final int BTN_SIGN_VIEW = BTN_DEV_WIDGET + 500;

    // 换行监听 View
    public static final int BTN_LINE_VIEW = BTN_DEV_WIDGET + 600;

    // 自定义点赞效果 View
    public static final int BTN_LIKE_VIEW = BTN_DEV_WIDGET + 700;

    // 自定义角标 View
    public static final int BTN_CORNER_LABEL_VIEW = BTN_DEV_WIDGET + 800;

    // View 填充辅助类
    public static final int BTN_VIEW_ASSIST          = BTN_DEV_WIDGET + 900;
    // RecyclerView ( loading )
    public static final int BTN_VIEW_ASSIST_RECYCLER = BTN_VIEW_ASSIST + 1;
    // Error ( failed )
    public static final int BTN_VIEW_ASSIST_ERROR    = BTN_VIEW_ASSIST + 2;
    // Empty ( data )
    public static final int BTN_VIEW_ASSIST_EMPTY    = BTN_VIEW_ASSIST + 3;
    // Custom Type
    public static final int BTN_VIEW_ASSIST_CUSTOM   = BTN_VIEW_ASSIST + 4;

    // ================================
    // = DevEnvironment 环境配置切换库 =
    // ================================

    // 环境配置切换
    public static final int BTN_DEV_ENVIRONMENT = MODULE_DEV_ENVIRONMENT;

    // 使用自定义配置
    public static final int BTN_USE_CUSTOM = BTN_DEV_ENVIRONMENT + 100;
}
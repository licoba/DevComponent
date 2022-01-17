package afkt_replace.core.app

/**
 * detail: APP 渠道信息
 * @author Ttt
 */
object AppUpgrade {

//    /**
//     * 获取升级版本号 ( 视情况而定, 为了区分 versionCode )
//     */
//    fun getUpgradeCode(): Long = BuildConfig.upgradeCode
}

/**
 * detail: App 版本信息
 * @author Ttt
 */
class AppVersionInfo(
    val content: String? = "", // 版本更新内容
    val isForce: Boolean? = false, // 是否强制升级
    val publishTimeMillis: Long = 0L, // 版本发布时间 ( 毫秒 )
    val channel: String? = "", // 渠道信息
    val versionCode: Int = 0, // APP 版本号
    val versionName: String? = "", // APP 版本名
    val id: Long = 0L, // 版本升级数据 id
    val fileMD5: String? = null, // APK MD5
    val fileSHA256: String? = null, // APK SHA256
    val fileUrl: String? = null, // APK 下载链接
    val type: Int = 0, // 自定义字段 ( 拓展区分类型 )
    val upgradeCode: Long = 0L, // 升级版本号
    val moduleId: Int = 0, // 模块 Id
    val moduleName: String? = "" // 模块名
)
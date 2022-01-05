package afkt_replace.core.app

import android.os.Parcelable
import afkt_replace.core.BuildConfig
import kotlinx.android.parcel.Parcelize

/**
 * detail: APP 渠道信息
 * @author Ttt
 */
object AppUpgrade {

    /**
     * 获取升级版本号 ( 视情况而定, 为了区分 versionCode )
     */
    fun getUpgradeCode(): Long = BuildConfig.upgradeCode
}

/**
 * detail: App 版本信息
 * @author Ttt
 */
@Parcelize
data class AppVersionInfo(
    val content: String? = "", // 版本更新内容
    val isForce: Boolean? = false, // 是否强制升级
    val upgradeCode: Long = 0L, // 升级版本号
    val publishTimeMillis: Long = 0L, // 版本发布时间 ( 毫秒 )
    val channel: String? = "", // 渠道信息
    val versionCode: Int = 0, // APP 版本号
    val versionName: String? = "", // APP 版本名
    var id: Long = 0L, // 版本升级数据 id
    var fileMD5: String? = null, // APK MD5
    var fileSHA256: String? = null, // APK SHA256
    var fileUrl: String? = null, // APK 下载链接
    var type: Int = 0, // 自定义字段 ( 拓展区分类型 )
) : Parcelable
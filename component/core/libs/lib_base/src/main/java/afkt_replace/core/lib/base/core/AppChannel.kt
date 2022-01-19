package afkt_replace.core.lib.base.core

import com.meituan.android.walle.ChannelInfo
import com.meituan.android.walle.WalleChannelReader
import dev.DevUtils
import dev.utils.common.MapUtils
import dev.utils.common.StringUtils

/**
 * detail: APP 渠道信息
 * @author Ttt
 */
object AppChannel {

    /**
     * 获取 APP 渠道信息
     */
    fun getChannelInfo(): ChannelInfo? {
        return WalleChannelReader.getChannelInfo(DevUtils.getContext())
    }

    /**
     * 获取 APP 渠道
     */
    fun getChannel(): String {
        return StringUtils.checkValue(getChannelInfo()?.channel)
    }

    /**
     * 获取 APP 渠道额外配置的信息
     */
    fun getExtraInfo(): MutableMap<String, String> {
        getChannelInfo()?.extraInfo?.let {
            if (MapUtils.isNotEmpty(it)) {
                return it
            }
        }
        return mutableMapOf()
    }

    /**
     * 获取 APP 渠道额外配置的信息
     * @param key 指定 Key
     */
    fun getExtraInfo(key: String?): String {
        return key?.let { itKey ->
            getExtraInfo().let { map ->
                StringUtils.checkValue(map[itKey])
            }
        } ?: ""
    }
}
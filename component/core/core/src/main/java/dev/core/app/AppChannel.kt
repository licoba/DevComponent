package dev.core.app

import android.text.TextUtils
import com.meituan.android.walle.ChannelInfo
import com.meituan.android.walle.WalleChannelReader
import dev.DevUtils
import dev.utils.common.MapUtils

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
        val channel = getChannelInfo()?.channel
        if (TextUtils.isEmpty(channel)) return ""
        return channel!!
    }

    /**
     * 获取 APP 渠道额外配置的信息
     */
    fun getExtraInfo(): MutableMap<String, String> {
        val extraInfo = getChannelInfo()?.extraInfo
        if (MapUtils.isNotEmpty(extraInfo)) return extraInfo!!
        return mutableMapOf()
    }

    /**
     * 获取 APP 渠道额外配置的信息
     * @param key 指定 Key
     */
    fun getExtraInfo(key: String?): String {
        return key?.let { itKey ->
            getExtraInfo().let { map ->
                map[itKey]
            }
        } ?: ""
    }
}
package afkt.push.router

import afkt.push.jpush.PushMessage
import afkt.push.jpush.toJson
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import dev.utils.DevFinal
import dev.utils.app.AppUtils

/**
 * detail: 推送消息路由处理 Activity
 * @author Ttt
 * 该 Activity 属于中转处理推送消息 ( 不需要继承 BaseActivity, 不需显示页面 ), 只用于推送路由分发
 */
class PushRouterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _intent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        _intent(intent)
    }

    /**
     * 推送 Intent 解析处理
     */
    private fun _intent(intent: Intent?) {
        PushRouterChecker.getLauncherClass()?.let { launcher ->
            intent?.let {
                // 获取推送数据
                it.getStringExtra(DevFinal.DATA)?.let { data ->
                    // 保存推送数据
                    SP.savePushData(this, data)

                    // ===========
                    // = 跳转处理 =
                    // ===========

                    val mainIntent = Intent(Intent.ACTION_MAIN)
                    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
                    mainIntent.component = ComponentName(
                        this.packageName,
                        launcher.canonicalName
                    )
//                    mainIntent.flags =
//                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                    mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(mainIntent)

                    // ===============
                    // = 关闭当前页面 =
                    // ===============

                    finish()
                }
            }
        }
    }

    companion object {

        /**
         * 跳转到推送消息路由处理 Activity
         * @param context [Context]
         * @param pushMessage  推送数据
         * @return `true` success, `false` fail
         */
        fun start(
            context: Context?,
            pushMessage: PushMessage,
        ): Boolean {
            val intent = Intent(context, PushRouterActivity::class.java)
            intent.putExtra(DevFinal.DATA, toJson(pushMessage))
            return AppUtils.startActivity(intent)
        }
    }
}

/**
 * detail: 推送路由校验
 * @author Ttt
 */
object PushRouterChecker {

    // LAUNCHER 页面 Class
    private var LAUNCHER_CLASS: Class<*>? = null

    // 推送回调接口
    private var pushCallback: IPushCallback? = null

    /**
     * 获取 LAUNCHER 类
     * @return android.intent.category.LAUNCHER class
     */
    fun getLauncherClass(): Class<*>? {
        return LAUNCHER_CLASS
    }

    /**
     * 设置 LAUNCHER 类
     * @param launcherClass android.intent.category.LAUNCHER
     */
    fun setLauncherClass(launcherClass: Class<*>?) {
        LAUNCHER_CLASS = launcherClass
    }

    /**
     * 设置回调
     * @param callback [IPushCallback]
     */
    fun setCallback(callback: IPushCallback?) {
        pushCallback = callback
    }

    /**
     * 设置回调
     * @param launcherClass android.intent.category.LAUNCHER
     * @param callback [IPushCallback]
     */
    fun setCallback(
        launcherClass: Class<*>?,
        callback: IPushCallback?
    ) {
        setLauncherClass(launcherClass)
        setCallback(callback)
    }

    // ========================
    // = BaseActivity 调用方法 =
    // ========================

    /**
     * 检查推送路由
     * @param activity [Activity]
     * @param activityClass
     */
    fun checker(
        activity: Activity?,
        activityClass: String?
    ) {
        activity?.let { context ->
            pushCallback?.run {
                if (isTriggerCallback(activityClass)) {
                    // 是否点击通知栏推送消息 ( 用于判断是否处理推送路由 )
                    if (SP.isClickPush(context)) {
                        var pushData = SP.getPushData(context)
                        // 触发回调
                        onCallback(context, pushData)
                    }
                }
            }
        }
    }
}

/**
 * detail: 推送回调接口
 * @author Ttt
 */
interface IPushCallback {

    /**
     * 是否触发回调
     * @param activityClass Activity 类名
     * @return `true` yes, `false` no
     */
    fun isTriggerCallback(activityClass: String?): Boolean

    /**
     * 推送回调方法
     * @param activity [Activity]
     * @param pushData 推送数据
     */
    fun onCallback(
        activity: Activity?,
        pushData: String?
    )
}

/**
 * detail: SharedPreferences 操作代码 ( 可以自行更换 db、mmkv )
 * @author Ttt
 */
private object SP {

    // SharedPreferences Name
    private const val NAME = "pushSP"

    // 是否点击通知栏推送消息 ( 用于判断是否处理推送路由 )
    private const val IS_CLICK_PUSH = "isClickPush"

    // 推送数据 ( JSON 格式 )
    private const val PUSH_DATA = "pushData"

    private fun _sp(context: Context): SharedPreferences {
        return context.getSharedPreferences(NAME, Activity.MODE_PRIVATE)
    }

    private fun clear(context: Context) {
        _sp(context)?.edit().clear().commit()
    }

    private fun getBoolean(
        context: Context,
        key: String,
        defValue: Boolean
    ): Boolean {
        return _sp(context)?.getBoolean(key, defValue)
    }

    private fun putBoolean(
        context: Context,
        key: String,
        value: Boolean
    ) {
        _sp(context)?.edit().putBoolean(key, value).commit()
    }

    private fun getString(
        context: Context,
        key: String,
        defValue: String?
    ): String? {
        return _sp(context)?.getString(key, defValue)
    }

    private fun putString(
        context: Context,
        key: String,
        value: String?
    ) {
        _sp(context)?.edit().putString(key, value).commit()
    }

    // ===============
    // = 对外公开方法 =
    // ===============

    /**
     * 是否点击通知栏推送消息 ( 用于判断是否处理推送路由 )
     */
    fun isClickPush(context: Context): Boolean {
        return getBoolean(context, IS_CLICK_PUSH, false)
    }

    /**
     * 保存推送数据
     */
    fun savePushData(
        context: Context,
        pushData: String
    ) {
        // 表示需要处理推送消息
        putBoolean(context, IS_CLICK_PUSH, true)
        // 保存推送数据
        putString(context, PUSH_DATA, pushData)
    }

    /**
     * 获取推送数据
     */
    fun getPushData(context: Context): String? {
        var pushData = getString(context, PUSH_DATA, null)
        clear(context) // 移除旧数据
        return pushData
    }
}
package afkt_replace.core.router

import afkt_replace.core.CoreConst
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * detail: 各个组件通讯接口
 * @author Ttt
 * Router IProvider 基础 Provider 类, 方便统一初始化控制、打印日志等
 */
interface BaseProvider : IProvider

/**
 * detail: BaseProvider 扩展类
 * @author Ttt
 */
open class BaseProviderExt(val TAG: String) {

    init {
        CoreConst.printProviderInitialize(TAG)
    }
}
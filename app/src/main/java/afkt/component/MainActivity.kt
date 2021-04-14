package afkt.component

import afkt.component.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter
import dev.core.lib.base.app.BaseActivity
import dev.core.lib.bean.AfkT
import dev.core.lib.config.aaa.AAARoute
import dev.utils.DevFinal

class MainActivity : BaseActivity<ActivityMainBinding, ViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar.setTitle("在吗").setBackListener { finish() }

//        // 当前渠道
//        ToastTintUtils.success(AppChannel.getChannel())

//        supportFragmentManager.beginTransaction()
//            .add(R.id.vid_am_frame, QRCodeScanFragment())
//            .commit()

        titleBar.postDelayed({
            ARouter.getInstance().build(AAARoute.LAUNCHER_PATH, AAARoute.GROUP)
                .withObject(DevFinal.DATA, AfkT("在吗"))
                .navigation()
        }, 2000L)
    }

    override fun baseLayoutId(): Int = R.layout.activity_main
}
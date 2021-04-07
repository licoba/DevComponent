package afkt.component

import afkt.component.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.lifecycle.ViewModel
import dev.core.lib.base.app.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, ViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar.setTitle("在吗").setBackListener { finish() }

//        // 当前渠道
//        ToastTintUtils.success(AppChannel.getChannel())
    }

    override fun baseLayoutId(): Int = R.layout.activity_main

    override fun initViewModel() {
    }
}
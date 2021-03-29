package afkt.component

import afkt.component.databinding.ActivityMainBinding
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModel
import dev.core.app.AppChannel
import dev.core.lib.base.BaseActivity
import dev.engine.image.DevImageEngine
import dev.utils.app.ViewUtils
import dev.utils.app.toast.ToastTintUtils

class MainActivity : BaseActivity<ActivityMainBinding, ViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        titleBar.setTitle("在吗").setBackListener { finish() }

        ViewUtils.setBackgroundColor(binding.root, Color.BLACK)

        DevImageEngine.getEngine().display(binding.vidAmIgview, "https://picsum.photos/201")

//        // 当前渠道
//        ToastTintUtils.success(AppChannel.getChannel())
    }

    override fun baseLayoutId(): Int = R.layout.activity_main

    override fun initViewModel() {
    }
}
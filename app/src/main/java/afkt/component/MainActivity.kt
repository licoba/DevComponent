package afkt.component

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import dev.core.app.AppChannel
import dev.engine.image.DevImageEngine
import dev.utils.app.toast.ToastTintUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val igview = ImageView(this)
        setContentView(igview)
        DevImageEngine.getEngine().display(igview, "https://picsum.photos/201")

        // 当前渠道
        ToastTintUtils.success(AppChannel.getChannel())
    }
}